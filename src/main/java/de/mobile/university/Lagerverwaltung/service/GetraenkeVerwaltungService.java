package de.mobile.university.Lagerverwaltung.service;

import de.mobile.university.Lagerverwaltung.config.AppConfig;
import de.mobile.university.Lagerverwaltung.exception.DrinkDuplicateException;
import de.mobile.university.Lagerverwaltung.exception.DrinkNotFoundException;
import de.mobile.university.Lagerverwaltung.exception.DrinkQuantityNegativeException;
import de.mobile.university.Lagerverwaltung.model.Getraenk;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;


public enum DrinkManagementService {

    INSTANCE();

    private final DrinkStorageService drinkStorageService;
    private ObservableList<Getraenk> getraenke;

    DrinkManagementService() {
        getraenke = FXCollections.observableArrayList(new ArrayList<>());
        drinkStorageService = new CsvDrinkStorageService();
        loadDrinks();
    }

    public DrinkManagementService getInstance() {
        return INSTANCE;
    }

    public ObservableList<Getraenk> getDrinks() {
        return getraenke;
    }

    private void loadDrinks() {
        if (new File(AppConfig.LAGER_CSV).exists()) {
            getraenke = FXCollections.observableArrayList(drinkStorageService.load(AppConfig.LAGER_CSV));
            sortByQuantity();
        } else {
            getraenke = FXCollections.observableArrayList(new ArrayList<>());
        }
    }

    private void sortByQuantity() {
        System.out.println("Sorting by quantity");
        this.getraenke.sort(Comparator.comparingInt(Getraenk::getQuantity).thenComparing(Getraenk::getName));
    }

    public synchronized void updateQuantity(String name, int quantity) {
        int index = findIndexByName(name);
        if (index != -1) {
            int newQuantity = getraenke.get(index).getQuantity() + quantity;
            System.out.println("Updating quantity of: " + name + " from: " + getraenke.get(index).getQuantity() + " to: " + newQuantity);
            if (newQuantity < 0) {
                throw new DrinkQuantityNegativeException(name);
            }
            getraenke.add(new Getraenk(name, newQuantity));
            getraenke.remove(index);
            sortByQuantity();
            drinkStorageService.save(getraenke);
        } else {
            throw new DrinkNotFoundException(name);
        }
    }

    private int findIndexByName(String name) {
        for (int i = 0; i < getraenke.size(); i++) {
            if (Objects.equals(getraenke.get(i).getName(), name)) {
                return i;
            }
        }
        return -1; // Not found
    }

    public void add(String name, int quantity) {
        System.out.println(("Adding: " + name + " with quantity: " + quantity));
        if (quantity < 0) {
            throw new DrinkQuantityNegativeException(name);
        }
        if (!drinkAlreadyExists(name)) {
            getraenke.add(new Getraenk(name, quantity));
        }
        sortByQuantity();
        drinkStorageService.save(getraenke);
    }

    private boolean drinkAlreadyExists(String name) {
        if (getraenke.stream().anyMatch(d -> Objects.equals(d.getName().toLowerCase(), name.toLowerCase()))) {
            throw new DrinkDuplicateException(name);
        }
        return false;
    }
}