package de.mobile.university.WarehouseManager.gui.controller;

import de.mobile.university.WarehouseManager.config.AppConfig;
import de.mobile.university.WarehouseManager.gui.view.AlertHelper;
import de.mobile.university.WarehouseManager.gui.view.DrinkManagementView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label dateLabel;

    public void initialize() {
        mainPane.setCenter(new DrinkManagementView());
        updateDate();
    }

    private void updateDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = dateFormat.format(new Date());
        dateLabel.setText(currentDate);
    }

    public void openAboutButton() {
        StringBuilder sb = new StringBuilder();
        sb.append(AppConfig.APP_NAME + " " + AppConfig.VERSION);
        sb.append("\nDeveloped by ").append(AppConfig.AUTHOR);
        sb.append("\nStorehouse icons created by srip - Flaticon https://www.flaticon.com/free-icons/storehouse");
        new AlertHelper(Alert.AlertType.INFORMATION, "About", sb.toString());
    }
}
