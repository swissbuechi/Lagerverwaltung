package de.mobile.university.WarehouseManager;

import de.mobile.university.WarehouseManager.gui.MainGui;
import de.mobile.university.WarehouseManager.service.ExternalImportService;
import javafx.application.Application;
import javafx.stage.Stage;

public class WarehouseManagerApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ExternalImportService externalImportService = new ExternalImportService();
        externalImportService.start();

        MainGui mainGui = new MainGui();
        mainGui.start(primaryStage);
    }
}
