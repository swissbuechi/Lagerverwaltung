package de.mobile.university.WarehouseManager.gui.view;

import de.mobile.university.WarehouseManager.model.Drink;
import de.mobile.university.WarehouseManager.service.DrinkManagementService;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

public class DrinkManagementView extends GridPane {

    private final DrinkManagementService drinkManagementService;

    public DrinkManagementView() {
        drinkManagementService = DrinkManagementService.INSTANCE.getInstance();
        createDrinkList();
        setAlignment(Pos.CENTER);
        setVgap(10);
        setHgap(10);
    }

    private void createDrinkList() {
        TableView<Drink> tableView = new TableView<>();
        tableView.setMinHeight(600);
        tableView.setMinWidth(1400);

        TableColumn<Drink, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

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

        tableView.getColumns().addAll(nameCol, quantityCol, changeCol);
        tableView.setEditable(true);

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
}