package de.mobile.university.WarehouseManager.exception;

import de.mobile.university.WarehouseManager.model.Drink;

public class DrinkQuantitiyNegativeException extends IllegalStateException {
    public DrinkQuantitiyNegativeException(String name) {
        super("Drink " + name + " has negative quantity!");
    }
}
