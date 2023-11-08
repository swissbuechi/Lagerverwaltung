package de.mobile.university.Lagerverwaltung.model;

public class Getraenk {
    private String name;
    private int anzahl;

    public Getraenk() {
    }

    public Getraenk(String name, int anzahl) {
        this.name = name;
        this.anzahl = anzahl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

}