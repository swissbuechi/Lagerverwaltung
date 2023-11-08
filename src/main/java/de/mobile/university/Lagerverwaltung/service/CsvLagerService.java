package de.mobile.university.Lagerverwaltung.service;

import de.mobile.university.Lagerverwaltung.konfiguration.AppKonfiguration;
import de.mobile.university.Lagerverwaltung.model.Getraenk;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvLagerService implements LagerService {

    //Speichert die Getraenke in einer CSV-Datei
    @Override
    public void speichern(List<Getraenk> getraenke) {
        System.out.println("Speichern in Datei: " + AppKonfiguration.LAGER_CSV);
        try (FileWriter writer = new FileWriter(AppKonfiguration.LAGER_CSV)) {
            writer.write("name,anzahl\n"); //Erstellt die Spaltennamen

            //Schreibt die Objekte pro Zeile in die CSV-Datei
            for (Getraenk getraenk : getraenke) {
                writer.write(getraenk.getName() + "," + getraenk.getAnzahl() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Liest die Getraenke aus einer CSV-Datei
    @Override
    public List<Getraenk> laden(String filename) {
        System.out.println("Lade aus Datei: " + filename);
        List<Getraenk> getraenke = new ArrayList<Getraenk>();
        BufferedReader leser = null;
        try {
            leser = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            // Lies erste Zeile
            String zeile = leser.readLine();

            // Ueberpruefe, ob die Datei die richtigen Spaltennamen hat
            if (zeile == null)
                throw new IllegalArgumentException("Datei ist leer");
            if (!zeile.equals("name,anzahl"))
                throw new IllegalArgumentException("File has wrong columns: " + zeile);

            // Liest alle weiteren Zeilen ein
            while ((zeile = leser.readLine()) != null) {

                // Trennt die Spalten an den Kommas
                String[] items = zeile.split(",");
                try {

                    if (items.length > 2)
                        throw new ArrayIndexOutOfBoundsException();

                    // Erstellt ein neues Getraenk-Objekt und fuegt es der
                    // Liste hinzu
                    getraenke.add(new Getraenk(items[0],
                            Integer.parseInt(items[1])));
                } catch (ArrayIndexOutOfBoundsException |
                         NumberFormatException |
                         NullPointerException e) {
                    System.out.println("Ungueltige Zeile: " + zeile);
                }
            }
            return getraenke;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                leser.close(); //Schliesst den BufferedReader
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
