package de.mobile.university.WarehouseManager.exception;

public class DrinkDuplicateException extends IllegalStateException{
    public DrinkDuplicateException(String name) {
        super("Drink " + name + " already exists!");
    }
}
