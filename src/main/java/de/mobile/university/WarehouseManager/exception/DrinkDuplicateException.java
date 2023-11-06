package de.mobile.university.WarehouseManager.exception;

import de.mobile.university.WarehouseManager.model.Drink;

public class DrinkDuplicateException extends IllegalStateException{
    public DrinkDuplicateException(String name) {
        super("Drink " + name + " already exists!");
    }
}
