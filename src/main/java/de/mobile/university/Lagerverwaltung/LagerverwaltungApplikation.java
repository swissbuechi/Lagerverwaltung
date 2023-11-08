package de.mobile.university.WarehouseManager;

import de.mobile.university.WarehouseManager.gui.MainGui;
import de.mobile.university.WarehouseManager.service.ExternalInventoryImportService;
import javafx.application.Application;
import javafx.stage.Stage;

public class WarehouseManagerApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ExternalInventoryImportService externalInventoryImportService = new ExternalInventoryImportService();
        externalInventoryImportService.start();

        MainGui mainGui = new MainGui();
        mainGui.start(primaryStage);
    }
}
