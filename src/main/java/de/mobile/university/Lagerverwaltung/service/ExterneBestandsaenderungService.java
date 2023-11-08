package de.mobile.university.Lagerverwaltung.service;

import de.mobile.university.Lagerverwaltung.konfiguration.AppKonfiguration;
import de.mobile.university.Lagerverwaltung.ausnahmen.GetraenkDuplikatException;
import de.mobile.university.Lagerverwaltung.ausnahmen.GetraenkNichtGefundenException;
import de.mobile.university.Lagerverwaltung.ausnahmen.GetraenkeBestandNegativException;
import javafx.application.Platform;

import java.io.File;

public class ExterneBestandsaenderungService extends Thread {
    private final GetraenkeVerwaltungService getraenkeVerwaltungService;
    private final LagerService lagerService;

    public ExterneBestandsaenderungService() {
        super("ExternalImportServiceThread");
        getraenkeVerwaltungService = GetraenkeVerwaltungService.INSTANCE.getInstance();
        lagerService = new CsvLagerService();
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
        File inventory = new File(AppKonfiguration.DONE_CSV);
        if (inventory.exists()) {
            lagerService.load(AppKonfiguration.DONE_CSV).forEach(drink -> {
                Platform.runLater(() -> {
                    try {
                        getraenkeVerwaltungService.updateQuantity(drink.getName(), drink.getAnzahl());
                    } catch (
                            GetraenkeBestandNegativException | GetraenkNichtGefundenException
                            | GetraenkDuplikatException e) {
                        e.printStackTrace();
                    }
                });
            });
            inventory.delete();
        }
    }
}