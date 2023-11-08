package de.mobile.university.Lagerverwaltung.service;

import de.mobile.university.Lagerverwaltung.config.AppConfig;
import de.mobile.university.Lagerverwaltung.exception.DrinkDuplicateException;
import de.mobile.university.Lagerverwaltung.exception.DrinkNotFoundException;
import de.mobile.university.Lagerverwaltung.exception.DrinkQuantityNegativeException;
import javafx.application.Platform;

import java.io.File;

public class ExternalInventoryImportService extends Thread {
    private final DrinkManagementService drinkManagementService;
    private final DrinkStorageService drinkStorageService;

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
        File inventory = new File(AppConfig.DONE_CSV);
        if (inventory.exists()) {
            drinkStorageService.load(AppConfig.DONE_CSV).forEach(drink -> {
                Platform.runLater(() -> {
                    try {
                        drinkManagementService.updateQuantity(drink.getName(), drink.getQuantity());
                    } catch (
                            DrinkQuantityNegativeException |
                            DrinkNotFoundException |
                            DrinkDuplicateException e) {
                        e.printStackTrace();
                    }
                });
            });
            inventory.delete();
        }
    }
}