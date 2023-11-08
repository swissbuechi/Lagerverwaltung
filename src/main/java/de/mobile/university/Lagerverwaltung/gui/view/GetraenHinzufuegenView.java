package de.mobile.university.Lagerverwaltung.gui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GetraenHinzufuegenView {

    public void showAddDrinkDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml" +
                    "/GetraenkHinzufuegen.fxml"));
            GridPane gridPane = loader.load();

            Stage getraenkHinzufuegenStage = new Stage();
            getraenkHinzufuegenStage.initModality(Modality.APPLICATION_MODAL);
            getraenkHinzufuegenStage.setTitle("Getraenk hinzufuegen");

            Scene scene = new Scene(gridPane, 300, 150);
            scene.getStylesheets().add(getClass().getResource("/css" +
                    "/stylesheet.css").toExternalForm());

            getraenkHinzufuegenStage.getIcons()
                    .add(new Image(getClass()
                            .getResourceAsStream("/images/logo.png")));

            getraenkHinzufuegenStage.setScene(scene);
            getraenkHinzufuegenStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
