package de.mobile.university.Lagerverwaltung.gui.controller;

import de.mobile.university.Lagerverwaltung.gui.view.GetraenkeUebersichtView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label dateLabel;

    public void initialize() {
        mainPane.setCenter(new GetraenkeUebersichtView());
        datumAktualisieren();
    }

    private void datumAktualisieren() {
        dateLabel.setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
    }
}
