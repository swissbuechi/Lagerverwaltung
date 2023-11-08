package de.mobile.university.Lagerverwaltung.service;

import de.mobile.university.Lagerverwaltung.konfiguration.AppKonfiguration;
import de.mobile.university.Lagerverwaltung.model.Getraenk;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvLagerService implements LagerService {

    @Override
    public void save(List<Getraenk> getraenke) {
        System.out.println("Saving to file: " + AppKonfiguration.LAGER_CSV);
        try (FileWriter writer = new FileWriter(AppKonfiguration.LAGER_CSV)) {

            // Write headers
            writer.write("name,anzahl\n");

            // Write data
            for (Getraenk getraenk : getraenke) {
                writer.write(getraenk.getName() + "," + getraenk.getAnzahl() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Getraenk> load(String filename) {
        System.out.println("Loading from file: " + filename);
        List<Getraenk> result = new ArrayList<Getraenk>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(filename)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            // Read first line
            String line = br.readLine();

            // Make sure file has correct headers
            if (line == null) throw new IllegalArgumentException("File is empty");
            if (!line.equals("name,anzahl"))
                throw new IllegalArgumentException("File has wrong columns: " + line);

            // Run through following lines
            while ((line = br.readLine()) != null) {

                // Break line into entries using comma
                String[] items = line.split(",");
                try {

                    // If there are too many entries, throw a dummy exception, if
                    // there are too few, the same exception will be thrown later
                    if (items.length > 2) throw new ArrayIndexOutOfBoundsException();

                    // Convert data to drink record
                    result.add(new Getraenk(items[0], Integer.parseInt(items[1])));
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException |
                         NullPointerException e) {

                    // Caught errors indicate a problem with data format -> Print warning and continue
                    System.out.println("Invalid line: " + line);
                }
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
