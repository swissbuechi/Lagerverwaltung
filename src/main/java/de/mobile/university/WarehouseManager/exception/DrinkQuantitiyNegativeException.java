package de.mobile.university.WarehouseManager.exception;

public class DrinkQuantitiyNegativeException extends IllegalStateException {
    public DrinkQuantitiyNegativeException(String name) {
        super("Drink " + name + " has negative quantity!");
    }
}
