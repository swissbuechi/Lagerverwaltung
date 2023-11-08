package de.mobile.university.WarehouseManager.service;

import de.mobile.university.WarehouseManager.config.AppConfig;
import de.mobile.university.WarehouseManager.exception.DrinkDuplicateException;
import de.mobile.university.WarehouseManager.exception.DrinkNotFoundException;
import de.mobile.university.WarehouseManager.exception.DrinkQuantityNegativeException;
import javafx.application.Platform;

import java.io.File;

public class ExternalInventoryImportService extends Thread {
    private DrinkManagementService drinkManagementService;
    private DrinkStorageService drinkStorageService;

    public ExternalInventoryImportService() {
        super("ExternalImportServiceThread");
        drinkManagementService = DrinkManagementService.INSTANCE.getInstance();
        drinkStorageService = new CsvDrinkStorageService();
    }

    public void run() {
        startSchedule();
    }

    private void startSchedule() {
        while (true) {
            try {
                Thread.sleep(60000);
                importDrinks();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void importDrinks() {
        File inventory = new File(AppConfig.EXTERNAL_INVENTORY_FILE);
        if (inventory.exists()) {
            drinkStorageService.load(AppConfig.EXTERNAL_INVENTORY_FILE).forEach(drink -> {
                Platform.runLater(() -> {
                    try {
                        drinkManagementService.updateQuantity(drink.getName(), drink.getQuantity());
                    } catch (DrinkQuantityNegativeException | DrinkNotFoundException | DrinkDuplicateException e) {
                        e.printStackTrace();
                    }
                });
            });
            inventory.delete();
        }
    }
}