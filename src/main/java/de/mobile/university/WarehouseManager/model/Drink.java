package de.mobile.university.WarehouseManager.model;

import de.mobile.university.WarehouseManager.exception.DrinkQuantitiyNegativeException;

public class Drink {
    private String name;
    private int quantity;

    public Drink() {
    }

    public Drink(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
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
        this.quantity = quantity;
    }

}