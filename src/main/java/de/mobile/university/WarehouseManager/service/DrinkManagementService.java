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

import java.io.File;
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
        loadDrinks();
    }

    public DrinkManagementService getInstance() {
        return INSTANCE;
    }

    public ObservableList<Drink> getDrinks() {
        return drinks;
    }

    private void loadDrinks() {
        if (new File(AppConfig.EXTERNAL_INVENTORY_FILE).exists()) {
            drinks = FXCollections.observableArrayList(drinkStorageService.load(AppConfig.INVENTORY_FILE));
            sortByQuantity();
        } else {
            drinks = FXCollections.observableArrayList(new ArrayList<>());
        }
    }

    private void sortByQuantity() {
        System.out.println("Sorting by quantity");
        this.drinks.sort(Comparator.comparingInt(Drink::getQuantity).thenComparing(Drink::getName));
    }

    public synchronized void updateQuantity(String name, int quantity) {
        int index = findIndexByName(name);
        if (index != -1) {
            int newQuantity = drinks.get(index).getQuantity() + quantity;
            System.out.println("Updating quantity of: " + name + " from: " + drinks.get(index).getQuantity() + " to: " + newQuantity);
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
        System.out.println(("Adding: " + name + " with quantity: " + quantity));
        if (quantity < 0) {
            throw new DrinkQuantitiyNegativeException(name);
        }
        if (!drinkAlreadyExists(name)) {
            drinks.add(new Drink(name, quantity));
        }
        sortByQuantity();
        drinkStorageService.save(drinks);
    }

    private boolean drinkAlreadyExists(String name) {
        if (drinks.stream().anyMatch(d -> Objects.equals(d.getName().toLowerCase(), name.toLowerCase()))) {
            throw new DrinkDuplicateException(name);
        }
        return false;
    }
}