package de.mobile.university.Lagerverwaltung.gui.controller;

import de.mobile.university.Lagerverwaltung.service.GetraenkeVerwaltungService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GetraenHinzufuegenController {

    private final GetraenkeVerwaltungService getraenkeVerwaltungService;

    @FXML
    private GridPane mainPane;

    @FXML
    private TextField nameFeld;

    @FXML
    private TextField anzahlFeld;

    public GetraenHinzufuegenController() {
        getraenkeVerwaltungService = GetraenkeVerwaltungService.INSTANCE.getInstance();
    }

    @FXML
    private void getraenkHinzufuegen() {
        String name = nameFeld.getText();
        int anzahl = Integer.parseInt(anzahlFeld.getText());
        getraenkeVerwaltungService.add(name, anzahl);
    }
}
