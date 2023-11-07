package de.mobile.university.WarehouseManager.exception;

/**
 * Custom exception class for handling duplicate drinks in the Warehouse Manager application.
 */
public class DrinkDuplicateException extends IllegalStateException {

    /**
     * Constructor for DrinkDuplicateException.
     *
     * @param name The name of the duplicate drink.
     */
    public DrinkDuplicateException(String name) {
        // Call the superclass constructor with a descriptive error message
        super("Drink " + name + " already exists!");
    }
}