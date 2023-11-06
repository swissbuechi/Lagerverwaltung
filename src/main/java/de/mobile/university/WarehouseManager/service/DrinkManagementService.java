package de.mobile.university.WarehouseManager.service;

import de.mobile.university.WarehouseManager.exception.DrinkDuplicateException;
import de.mobile.university.WarehouseManager.exception.DrinkNotFoundException;
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
    }

    public DrinkManagementService getInstance() {
        return INSTANCE;
    }

    public ObservableList<Drink> getDrinks() {
        drinks = FXCollections.observableArrayList(drinkStorageService.load());
        sortByQuantity();
        return drinks;
    }

    private void sortByQuantity() {
        this.drinks.sort(Comparator.comparingInt(Drink::getQuantity)
                .thenComparing(Drink::getName));
    }

    public void updateQuantity(String name, int quantity) {
        int index = findIndexByName(name);
        if (index != -1) {
            drinks.add(
                    new Drink() {{
                        setName(name);
                        setQuantity(quantity);
                    }}
            );
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