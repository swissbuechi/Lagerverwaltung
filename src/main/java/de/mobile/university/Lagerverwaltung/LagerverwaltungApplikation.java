package de.mobile.university.Lagerverwaltung;

import de.mobile.university.Lagerverwaltung.gui.MainGui;
import de.mobile.university.Lagerverwaltung.service.ExterneBestandsaenderungService;
import javafx.application.Application;
import javafx.stage.Stage;

public class LagerverwaltungApplikation extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ExterneBestandsaenderungService externeBestandsaenderungService =
                new ExterneBestandsaenderungService();
        externeBestandsaenderungService.start();

        MainGui mainGui = new MainGui();
        mainGui.start(primaryStage);
    }
}
