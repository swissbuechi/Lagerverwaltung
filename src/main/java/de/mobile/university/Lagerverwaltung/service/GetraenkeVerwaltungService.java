package de.mobile.university.Lagerverwaltung.service;

import de.mobile.university.Lagerverwaltung.ausnahmen.GetraenkDuplikatException;
import de.mobile.university.Lagerverwaltung.ausnahmen.GetraenkNichtGefundenException;
import de.mobile.university.Lagerverwaltung.ausnahmen.GetraenkeBestandNegativException;
import de.mobile.university.Lagerverwaltung.konfiguration.AppKonfiguration;
import de.mobile.university.Lagerverwaltung.model.Getraenk;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;


public enum GetraenkeVerwaltungService {

    INSTANCE();

    private final LagerService lagerService;
    private ObservableList<Getraenk> getraenke;

    GetraenkeVerwaltungService() {
        getraenke = FXCollections.observableArrayList(new ArrayList<>());
        lagerService = new CsvLagerService();
        ladeGetraenke();
    }

    public GetraenkeVerwaltungService getInstance() {
        return INSTANCE;
    }

    public ObservableList<Getraenk> getGetraenke() {
        return getraenke;
    }

    // Laedt die Getraenke aus der CSV-Datei wenn diese existiert
    private void ladeGetraenke() {
        if (new File(AppKonfiguration.LAGER_CSV).exists()) {
            getraenke =
                    FXCollections.observableArrayList(lagerService
                            .laden(AppKonfiguration.LAGER_CSV));
            sortierenNachAnzahlUndName();
        } else {
            getraenke = FXCollections.observableArrayList(new ArrayList<>());
        }
    }

    // Aktualisiert die Anzahl eines Getraenks
    public synchronized void bestandesaenderung(String name,
                                                int bestandesaenderung) {
        int index = findeIndexAnhandName(name);
        if (index != -1) { // Getraenk gefunden
            // Aktuelle Anzahl + Bestandsaenderung = neue Anzahl
            int neueAnzahl =
                    getraenke.get(index).getAnzahl() + bestandesaenderung;
            System.out.println("Bestandesenderung: " + name + " from: " +
                    getraenke.get(index).getAnzahl() + " to: " + neueAnzahl);
            if (neueAnzahl < 0) {
                throw new GetraenkeBestandNegativException(name);
            }
            // getraenke.get(index).setAnzahl(neueAnzahl); // Funktioniert
            // leider nicht mit ObservableList
            getraenke.remove(index);
            getraenke.add(new Getraenk(name, neueAnzahl));
            sortierenNachAnzahlUndName();
            lagerService.speichern(getraenke); // Speichern der Getraenke im CSV
        } else {
            throw new GetraenkNichtGefundenException(name);
        }
    }

    // Findet den Index eines Getraenks andhand vom Namen
    private int findeIndexAnhandName(String name) {
        for (int i = 0; i < getraenke.size(); i++) {
            if (Objects.equals(getraenke.get(i).getName(), name)) {
                return i;
            }
        }
        return -1;// Nicht gefunden
    }

    // Fuegt ein neues Getraenk hinzu
    public void hinzufuegen(Getraenk getraenk) {
        System.out.println(("Hinzufuegen: " + getraenk.getName() + " mit der " +
                "Anzahl: " + getraenk.getAnzahl()));
        if (getraenk.getAnzahl() < 0) {
            throw new GetraenkeBestandNegativException(getraenk.getName());
        }
        if (!getraenkBereitsVorhanden(getraenk.getName())) {
            getraenke.add(getraenk);
        }
        sortierenNachAnzahlUndName();
        lagerService.speichern(getraenke); // Speichern der Getraenke in der CSV-Datei
    }

    private void sortierenNachAnzahlUndName() { //Tiefere Anzahl zuerst
        System.out.println("Sortieren nach Anzahl und Name");
        this.getraenke.sort(Comparator.comparingInt(Getraenk::getAnzahl)
                .thenComparing(Getraenk::getName));
    }

    // Ueberprueft, ob ein Getraenk bereits vorhanden ist
    // (Gross-/Kleinschreibung wird ignoriert)
    private boolean getraenkBereitsVorhanden(String name) {
        if (getraenke.stream().anyMatch(d -> Objects.equals(d.getName().toLowerCase(),
                name.toLowerCase()))) {
            throw new GetraenkDuplikatException(name);
        }
        return false;
    }
}