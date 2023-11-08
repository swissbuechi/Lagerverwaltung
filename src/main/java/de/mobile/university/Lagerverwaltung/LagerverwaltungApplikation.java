package de.mobile.university.Lagerverwaltung;

import de.mobile.university.Lagerverwaltung.gui.MainGui;
import de.mobile.university.Lagerverwaltung.service.ExterneBestandsaenderungService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class LagerverwaltungApplikation extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // Startet die GUI und den Service fuer die externe Bestandsaenderung
    @Override
    public void start(Stage primaryStage) {
        ExterneBestandsaenderungService externeBestandsaenderungService =
                new ExterneBestandsaenderungService();
        externeBestandsaenderungService.start();

        MainGui mainGui = new MainGui();
        try {
            mainGui.start(primaryStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}