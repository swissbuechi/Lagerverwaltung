package de.mobile.university.WarehouseManager.exception;

public class DrinkNotFoundException extends NullPointerException{
    public DrinkNotFoundException(String name) {
        super("Drink " + name + " not found!");
    }
}
