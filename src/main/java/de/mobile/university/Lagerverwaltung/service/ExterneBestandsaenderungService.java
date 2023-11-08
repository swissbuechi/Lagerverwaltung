package de.mobile.university.Lagerverwaltung.service;

import de.mobile.university.Lagerverwaltung.ausnahmen.GetraenkDuplikatException;
import de.mobile.university.Lagerverwaltung.ausnahmen.GetraenkNichtGefundenException;
import de.mobile.university.Lagerverwaltung.ausnahmen.GetraenkeBestandNegativException;
import de.mobile.university.Lagerverwaltung.konfiguration.AppKonfiguration;
import javafx.application.Platform;

import java.io.File;

public class ExterneBestandsaenderungService extends Thread {
    private final GetraenkeVerwaltungService getraenkeVerwaltungService;
    private final LagerService lagerService;

    public ExterneBestandsaenderungService() {
        super("ExterneBestandsaenderungService");
        getraenkeVerwaltungService =
                GetraenkeVerwaltungService.INSTANCE.getInstance();
        lagerService = new CsvLagerService();
    }

    public void run() {
        startSchedule();
    }

    // Startet den Schedule-Thread (alle 60 Sekunden)
    private void startSchedule() {
        while (true) {
            try {
                Thread.sleep(60000);
                importiereGetraenke();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Importiert die Getraenke aus der CSV-Datei wenn diese existiert
    private void importiereGetraenke() {
        File bestandaenderung = new File(AppKonfiguration.DONE_CSV);
        if (bestandaenderung.exists()) {
            lagerService.laden(AppKonfiguration.DONE_CSV).forEach(drink -> {

                // JavaFX-Thread
                Platform.runLater(() -> {
                    try {
                        getraenkeVerwaltungService.bestandesaenderung(drink.getName(),
                                drink.getAnzahl());
                    } catch (GetraenkeBestandNegativException |
                             GetraenkNichtGefundenException |
                             GetraenkDuplikatException e) {
                        e.printStackTrace();
                    }
                });
            });
            bestandaenderung.delete();
        }
    }
}