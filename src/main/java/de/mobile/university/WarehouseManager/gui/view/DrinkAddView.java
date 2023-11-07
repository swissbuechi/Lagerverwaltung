package de.mobile.university.WarehouseManager.gui.view;

import de.mobile.university.WarehouseManager.model.Drink;
import de.mobile.university.WarehouseManager.service.DrinkManagementService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DrinkAddView {

    private final DrinkManagementService drinkManagementService;

    public DrinkAddView(DrinkManagementService drinkManagementService) {
        this.drinkManagementService = drinkManagementService;
    }

    public void showAddDrinkDialog() {
        Stage addDrinkStage = new Stage();
        addDrinkStage.initModality(Modality.APPLICATION_MODAL);
        addDrinkStage.setTitle("Add New Drink");

        // Apply stylesheet
        Scene scene = new Scene(createGridPane(), 300, 150);
        scene.getStylesheets().add(getClass().getResource("/css/stylesheet.css").toExternalForm());

        // Apply icon
        addDrinkStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));

        addDrinkStage.setScene(scene);
        addDrinkStage.showAndWait();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label quantityLabel = new Label("Quantity:");
        TextField quantityField = new TextField();

        Button addButton = new Button("Add Drink");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            drinkManagementService.add(name, quantity);
        });

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(quantityLabel, 0, 1);
        gridPane.add(quantityField, 1, 1);
        gridPane.add(addButton, 1, 2);

        return gridPane;
    }
}
