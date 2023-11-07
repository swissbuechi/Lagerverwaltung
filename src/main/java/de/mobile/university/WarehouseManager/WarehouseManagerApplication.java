package de.mobile.university.WarehouseManager;

import de.mobile.university.WarehouseManager.gui.MainGui;
import de.mobile.university.WarehouseManager.service.ExternalImportService;

public class WarehouseManagerApplication {

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        ExternalImportService ExternalImportService = new ExternalImportService();
//        ExternalImportService.start();
        MainGui mainGui = new MainGui();
        mainGui.show();
    }
}