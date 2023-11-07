package de.mobile.university.WarehouseManager.storage;

import de.mobile.university.WarehouseManager.config.AppConfig;
import de.mobile.university.WarehouseManager.model.Drink;

import java.io.*;
import java.util.*;

public class CsvDrinkStorageService implements DrinkStorageService {

    @Override
    public void save(List<Drink> drinks) {
        System.out.println("Saving to file: " + AppConfig.INVENTORY_FILE);
        try (FileWriter writer = new FileWriter(AppConfig.INVENTORY_FILE)) {
            // Write headers
            writer.write("name,quantity\n");

            // Write data
            for (Drink drink : drinks) {
                writer.write(drink.getName() + "," + drink.getQuantity() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Drink> load(String filename) {
        System.out.println("Loading from file: " + filename);
        List<Drink> result = new ArrayList<Drink>();
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
            if (!line.equals("name,quantity"))
                throw new IllegalArgumentException("File has wrong columns: " + line);
            // Run through following lines
            while ((line = br.readLine()) != null) {
                // Break line into entries using comma
                String[] items = line.split(",");
                try {
                    // If there are too many entries, throw a dummy exception, if
                    // there are too few, the same exception will be thrown later
                    if (items.length > 4) throw new ArrayIndexOutOfBoundsException();
                    // Convert data to drink record
                    Drink drink = new Drink();
                    drink.setName(items[0]);
                    drink.setQuantity(Integer.parseInt(items[1]));
                    result.add(drink);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
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
