package de.mobile.university.Lagerverwaltung.exception;

public class GetraenkDuplikatException extends IllegalStateException {

    public GetraenkDuplikatException(String name) {
        super("Getraenk " + name + " bereits vorhanden!");
    }
}