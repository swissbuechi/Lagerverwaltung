package de.mobile.university.Lagerverwaltung.ausnahmen;

public class GetraenkeBestandNegativException extends IllegalStateException {

    public GetraenkeBestandNegativException(String name) {
        super("Getraenk " + name + " hat einen negativen Bestand!");
    }
}