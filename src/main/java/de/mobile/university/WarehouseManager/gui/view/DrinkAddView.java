// DrinkAddView.java
package de.mobile.university.WarehouseManager.gui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DrinkAddView {

    public void showAddDrinkDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DrinkAddView.fxml"));
            GridPane gridPane = loader.load();

            Stage addDrinkStage = new Stage();
            addDrinkStage.initModality(Modality.APPLICATION_MODAL);
            addDrinkStage.setTitle("Add New Drink");

            // Apply stylesheet
            Scene scene = new Scene(gridPane, 300, 150);
            scene.getStylesheets().add(getClass().getResource("/css/stylesheet.css").toExternalForm());

            // Apply icon
            addDrinkStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));

            // Set scene
            addDrinkStage.setScene(scene);
            addDrinkStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
