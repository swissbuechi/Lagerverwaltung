package de.mobile.university.WarehouseManager.gui.view;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AlertHelper extends Alert {

    public AlertHelper(AlertType alertType, Window owner, String title, String message) {
        super(alertType);
        this.setTitle(title);
        this.setHeaderText(null);
        this.setContentText(message);
        this.initOwner(owner);
        DialogPane dialogPane = this.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/css/stylesheet.css").toExternalForm());
        Stage stage = (Stage) dialogPane.getScene().getWindow();
        stage.getIcons().add(
                new Image(this.getClass().getResource("/images/logo.png").toString()));
        this.showAndWait();
    }

    public AlertHelper(AlertType alertType, String title, String message) {
        super(alertType);
        this.setTitle(title);
        this.setHeaderText(null);
        this.setContentText(message);
        DialogPane dialogPane = this.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/css/stylesheet.css").toExternalForm());
        Stage stage = (Stage) dialogPane.getScene().getWindow();
        stage.getIcons().add(
                new Image(this.getClass().getResource("/images/logo.png").toString()));
        this.showAndWait();
    }
}
