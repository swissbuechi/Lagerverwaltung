package de.mobile.university.Lagerverwaltung.service;

import de.mobile.university.Lagerverwaltung.model.Getraenk;

import java.util.List;

public interface LagerService {
    void save(List<Getraenk> getraenke);

    List<Getraenk> load(String filename);
}
