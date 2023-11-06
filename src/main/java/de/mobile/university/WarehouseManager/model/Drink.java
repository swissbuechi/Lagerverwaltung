package de.mobile.university.WarehouseManager.model;

import de.mobile.university.WarehouseManager.exception.DrinkQuantitiyNegativeException;

public class Drink {
    private String name;
    private int quantity;

    public Drink() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new DrinkQuantitiyNegativeException(this.name);
        }
        this.quantity = quantity;
    }
}