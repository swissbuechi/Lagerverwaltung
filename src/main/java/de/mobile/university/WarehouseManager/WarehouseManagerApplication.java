package de.mobile.university.WarehouseManager;

import de.mobile.university.WarehouseManager.gui.MainGui;

public class WarehouseManagerApplication {

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        MainGui mainGui = new MainGui();
        mainGui.show();
    }
}
