package de.mobile.university.WarehouseManager.exception;

/**
 * Custom exception class for handling cases where a drink is not found in the Warehouse Manager application.
 */
public class DrinkNotFoundException extends NullPointerException {

    /**
     * Constructor for DrinkNotFoundException.
     *
     * @param name The name of the drink that was not found.
     */
    public DrinkNotFoundException(String name) {
        // Call the superclass constructor with a descriptive error message
        super("Drink " + name + " not found!");
    }
}