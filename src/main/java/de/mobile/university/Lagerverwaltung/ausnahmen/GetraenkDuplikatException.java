package de.mobile.university.Lagerverwaltung.ausnahmen;

public class GetraenkDuplikatException extends IllegalStateException {

    public GetraenkDuplikatException(String name) {
        super("Getraenk " + name + " bereits vorhanden!");
    }
}