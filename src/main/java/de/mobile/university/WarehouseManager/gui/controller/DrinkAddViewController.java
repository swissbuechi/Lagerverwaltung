// DrinkAddViewController.java
package de.mobile.university.WarehouseManager.gui.controller;

import de.mobile.university.WarehouseManager.service.DrinkManagementService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class DrinkAddViewController {

    private DrinkManagementService drinkManagementService;

    @FXML
    private GridPane mainPane;

    @FXML
    private TextField nameField;

    @FXML
    private TextField quantityField;

    public DrinkAddViewController() {
        drinkManagementService = DrinkManagementService.INSTANCE.getInstance();
    }

    @FXML
    private void addDrink() {
        String name = nameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        drinkManagementService.add(name, quantity);
    }
}
