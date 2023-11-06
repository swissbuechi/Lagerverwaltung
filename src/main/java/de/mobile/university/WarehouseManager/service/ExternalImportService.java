package de.mobile.university.WarehouseManager.service;

import de.mobile.university.WarehouseManager.exception.DrinkDuplicateException;
import de.mobile.university.WarehouseManager.exception.DrinkNotFoundException;
import de.mobile.university.WarehouseManager.exception.DrinkQuantitiyNegativeException;

public class ExternalImportService extends Thread {
    private DrinkManagementService drinkManagementService;

    public ExternalImportService() {
        drinkManagementService = DrinkManagementService.INSTANCE.getInstance();
    }

    public void run() {
        importDrink();
    }

    private void importDrink() {
        while (true) {
            try {
                Thread.sleep(2000);
                drinkManagementService.add("ABC", 5);
                Thread.sleep(2000);
                drinkManagementService.updateQuantity("ABC", -5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (DrinkQuantitiyNegativeException e) {
                e.printStackTrace();
            } catch (DrinkNotFoundException e) {
                e.printStackTrace();
            } catch (DrinkDuplicateException e) {
                e.printStackTrace();
            }
        }
    }
}
