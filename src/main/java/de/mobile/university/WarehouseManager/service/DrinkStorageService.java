package de.mobile.university.WarehouseManager.service;

import de.mobile.university.WarehouseManager.model.Drink;

import java.util.List;

public interface DrinkStorageService {
    void save(List<Drink> drinks);

    List<Drink> load(String filename);
}
