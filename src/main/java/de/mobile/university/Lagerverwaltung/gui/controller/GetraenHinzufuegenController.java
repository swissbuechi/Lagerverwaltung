package de.mobile.university.Lagerverwaltung.gui.controller;

import de.mobile.university.Lagerverwaltung.model.Getraenk;
import de.mobile.university.Lagerverwaltung.service.GetraenkeVerwaltungService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GetraenHinzufuegenController {

    private final GetraenkeVerwaltungService getraenkeVerwaltungService;

    @FXML
    private TextField nameFeld;

    @FXML
    private TextField anzahlFeld;

    public GetraenHinzufuegenController() {
        getraenkeVerwaltungService =
                GetraenkeVerwaltungService.INSTANCE.getInstance();
    }

    // Speichert das Getraenk, welches in den Textfeldern eingegeben wurde
    @FXML
    private void getraenkHinzufuegen() {
        String name = nameFeld.getText();
        int anzahl = Integer.parseInt(anzahlFeld.getText());
        getraenkeVerwaltungService.hinzufuegen(new Getraenk(name, anzahl));
    }
}
