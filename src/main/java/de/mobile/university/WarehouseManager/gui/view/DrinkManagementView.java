package de.mobile.university.WarehouseManager.gui.view;

import de.mobile.university.WarehouseManager.model.Drink;
import de.mobile.university.WarehouseManager.service.DrinkManagementService;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;


public class DrinkManagementView extends GridPane {


    private DrinkManagementService drinkManagementService;

    private TableView tableView;

    public DrinkManagementView() {
        drinkManagementService = DrinkManagementService.INSTANCE.getInstance();
        createDrinkList();
        setAlignment(Pos.CENTER);
        setVgap(10);
        setHgap(10);
    }

    private void createDrinkList() {
        tableView = new TableView<>();
        tableView.setMinHeight(600);
        tableView.setMinWidth(1400);

//        tableView.getSelectionModel().selectedItemProperty().addListener(
//                (obs, oldSelection, newSelection) -> change(obs, oldSelection, newSelection));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn quantity = new TableColumn("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableView.getColumns().addAll(name, quantity);

        tableView.setItems(drinkManagementService.getDrinks());

        add(tableView, 0, 0, 2, 1);
    }
}
