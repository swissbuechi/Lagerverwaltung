package de.mobile.university.Lagerverwaltung.exception;

public class GetraenkNichtGefundenException extends NullPointerException {

    public GetraenkNichtGefundenException(String name) {
        super("Getraenk " + name + " nicht gefunden!");
    }
}