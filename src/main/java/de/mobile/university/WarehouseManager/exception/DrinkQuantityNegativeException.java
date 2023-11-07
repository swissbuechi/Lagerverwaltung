package de.mobile.university.WarehouseManager.exception;

/**
 * Custom exception class for handling cases where the quantity of a drink becomes negative in the Warehouse Manager application.
 */
public class DrinkQuantityNegativeException extends IllegalStateException {

    /**
     * Constructor for DrinkQuantityNegativeException.
     *
     * @param name The name of the drink with a negative quantity.
     */
    public DrinkQuantityNegativeException(String name) {
        // Call the superclass constructor with a descriptive error message
        super("Drink " + name + " has negative quantity!");
    }
}