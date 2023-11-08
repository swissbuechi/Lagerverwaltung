package de.mobile.university.Lagerverwaltung.service;

import de.mobile.university.Lagerverwaltung.konfiguration.AppKonfiguration;
import de.mobile.university.Lagerverwaltung.ausnahmen.GetraenkDuplikatException;
import de.mobile.university.Lagerverwaltung.ausnahmen.GetraenkNichtGefundenException;
import de.mobile.university.Lagerverwaltung.ausnahmen.GetraenkeBestandNegativException;
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
        loadDrinks();
    }

    public GetraenkeVerwaltungService getInstance() {
        return INSTANCE;
    }

    public ObservableList<Getraenk> getDrinks() {
        return getraenke;
    }

    private void loadDrinks() {
        if (new File(AppKonfiguration.LAGER_CSV).exists()) {
            getraenke = FXCollections.observableArrayList(lagerService.load(AppKonfiguration.LAGER_CSV));
            sortByQuantity();
        } else {
            getraenke = FXCollections.observableArrayList(new ArrayList<>());
        }
    }

    private void sortByQuantity() {
        System.out.println("Sorting by quantity");
        this.getraenke.sort(Comparator.comparingInt(Getraenk::getAnzahl)
                .thenComparing(Getraenk::getName));
    }

    public synchronized void updateQuantity(String name, int quantity) {
        int index = findIndexByName(name);
        if (index != -1) {
            int newQuantity = getraenke.get(index).getAnzahl() + quantity;
            System.out.println("Updating quantity of: " + name + " from: " +
                    getraenke.get(index).getAnzahl() + " to: " + newQuantity);
            if (newQuantity < 0) {
                throw new GetraenkeBestandNegativException(name);
            }
            getraenke.add(new Getraenk(name, newQuantity));
            getraenke.remove(index);
            sortByQuantity();
            lagerService.save(getraenke);
        } else {
            throw new GetraenkNichtGefundenException(name);
        }
    }

    private int findIndexByName(String name) {
        for (int i = 0; i < getraenke.size(); i++) {
            if (Objects.equals(getraenke.get(i).getName(), name)) {
                return i;
            }
        }
        return -1; // Not found
    }

    public void add(String name, int quantity) {
        System.out.println(("Adding: " + name + " with quantity: " + quantity));
        if (quantity < 0) {
            throw new GetraenkeBestandNegativException(name);
        }
        if (!drinkAlreadyExists(name)) {
            getraenke.add(new Getraenk(name, quantity));
        }
        sortByQuantity();
        lagerService.save(getraenke);
    }

    private boolean drinkAlreadyExists(String name) {
        if (getraenke.stream().anyMatch(d -> Objects.equals
                (d.getName().toLowerCase(), name.toLowerCase()))) {
            throw new GetraenkDuplikatException(name);
        }
        return false;
    }
}