package de.mobile.university.WarehouseManager.service;

import de.mobile.university.WarehouseManager.config.AppConfig;
import de.mobile.university.WarehouseManager.exception.DrinkDuplicateException;
import de.mobile.university.WarehouseManager.exception.DrinkNotFoundException;
import de.mobile.university.WarehouseManager.exception.DrinkQuantitiyNegativeException;
import de.mobile.university.WarehouseManager.model.Drink;
import de.mobile.university.WarehouseManager.storage.CsvDrinkStorageService;
import de.mobile.university.WarehouseManager.storage.DrinkStorageService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;


public enum DrinkManagementService {

    INSTANCE();

    private ObservableList<Drink> drinks;
    private final DrinkStorageService drinkStorageService;

    private DrinkManagementService() {
        drinks = FXCollections.observableArrayList(new ArrayList<>());
        drinkStorageService = new CsvDrinkStorageService();
        drinks = FXCollections.observableArrayList(drinkStorageService.load(AppConfig.INVENTORY_FILE));
        sortByQuantity();
    }

    public DrinkManagementService getInstance() {
        return INSTANCE;
    }

    public ObservableList<Drink> getDrinks() {
        return drinks;
    }

    private void sortByQuantity() {
        System.out.println("Sorting drinks");
        this.drinks.sort(Comparator.comparingInt(Drink::getQuantity)
                .thenComparing(Drink::getName));
    }

    public synchronized void updateQuantity(String name, int quantity) {
        int index = findIndexByName(name);
        if (index != -1) {
            int newQuantity = drinks.get(index).getQuantity() + quantity;
            System.out.println("Updating quantity of " + name + " to + (" + quantity + ") = (" + newQuantity + ")");
            if (newQuantity < 0) {
                throw new DrinkQuantitiyNegativeException(name);
            }
            drinks.add(new Drink(name, newQuantity));
            drinks.remove(index);
            sortByQuantity();
            drinkStorageService.save(drinks);
        } else {
            throw new DrinkNotFoundException(name);
        }
    }

    private int findIndexByName(String name) {
        for (int i = 0; i < drinks.size(); i++) {
            if (Objects.equals(drinks.get(i).getName(), name)) {
                return i;
            }
        }
        return -1; // Not found
    }

    public void add(String name, int quantity) {
        if (!drinkAlreadyExists(name)) {
            drinks.add(
                    new Drink() {{
                        setName(name);
                        setQuantity(quantity);
                    }}
            );
        }
        sortByQuantity();
        drinkStorageService.save(drinks);
    }

    private boolean drinkAlreadyExists(String name) {
        if (drinks.stream().anyMatch(d -> Objects.equals(d.getName(), name))) {
            throw new DrinkDuplicateException(name);
        }
        return false;
    }
}