package de.mobile.university.WarehouseManager.gui.view;

import de.mobile.university.WarehouseManager.model.Drink;
import de.mobile.university.WarehouseManager.service.DrinkManagementService;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.util.List;

public class DrinkManagementView extends GridPane {

    private final DrinkManagementService drinkManagementService;
    private final DrinkAddView drinkAddView;

    public DrinkManagementView() {
        drinkManagementService = DrinkManagementService.INSTANCE.getInstance();
        drinkAddView = new DrinkAddView(drinkManagementService);
        createDrinkList();
        createTotalQuantityLabel();
        createAddButton();
        createExitButton();
        setAlignment(Pos.CENTER);
        setVgap(10);
        setHgap(10);
    }

    private void createDrinkList() {
        TableView<Drink> tableView = new TableView<>();
        tableView.setPrefWidth(1100);
        tableView.setPrefHeight(500);

        TableColumn<Drink, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Set column to take the available width
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Drink, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Drink, Void> changeCol = new TableColumn<>("Change");
        changeCol.setCellFactory(col -> new TableCell<>() {
            private final TextField textField = new TextField();

            {
                textField.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.ENTER) {
                        int changeValue = Integer.parseInt(textField.getText());
                        Drink drink = getTableView().getItems().get(getIndex());
                        drinkManagementService.updateQuantity(drink.getName(), changeValue);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(textField);
                    textField.setText(""); // Clear the text field for reuse
                }
            }
        });

        // Add columns to table
        tableView.getColumns().add(nameCol);
        tableView.getColumns().add(quantityCol);
        tableView.getColumns().add(changeCol);

        // Disable sorting
        nameCol.setSortable(false);
        quantityCol.setSortable(false);
        changeCol.setSortable(false);

        // Disable reordering
        nameCol.setReorderable(false);
        quantityCol.setReorderable(false);
        changeCol.setReorderable(false);

        // Disable selection
        tableView.setSelectionModel(null);
        tableView.setItems(drinkManagementService.getDrinks());

        add(tableView, 0, 0, 2, 1);
    }

    private void createTotalQuantityLabel() {
        int totalQuantity = calculateTotalQuantity(drinkManagementService.getDrinks());
        Label totalLabel = new Label("Total Quantity: " + totalQuantity);

        // Add a listener to update the label when the underlying list changes
        drinkManagementService.getDrinks().addListener((ListChangeListener<Drink>) c -> {
            while (c.next()) {
                if (c.wasUpdated() || c.wasAdded() || c.wasRemoved()) {
                    int newTotalQuantity = calculateTotalQuantity(drinkManagementService.getDrinks());
                    totalLabel.setText("Total Quantity: " + newTotalQuantity);
                }
            }
        });

        // Set the alignment of the label to center
        setHalignment(totalLabel, javafx.geometry.HPos.CENTER);
        add(totalLabel, 0, 1, 2, 1);
    }

    private void createAddButton() {
        Button addDrinkButton = new Button("Add Drink");
        addDrinkButton.setOnAction(e -> drinkAddView.showAddDrinkDialog());
        add(addDrinkButton, 0, 2);
    }

    private void createExitButton() {
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> System.exit(0));

        // Set the horizontal alignment to RIGHT for the exit button
        setHalignment(exitButton, javafx.geometry.HPos.RIGHT);
        add(exitButton, 1, 2);
    }

    private int calculateTotalQuantity(List<Drink> drinks) {
        return drinks.stream().mapToInt(Drink::getQuantity).sum();
    }
}
