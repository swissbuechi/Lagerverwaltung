package de.mobile.university.WarehouseManager.storage;

import de.mobile.university.WarehouseManager.model.Drink;

import java.util.*;

public class CsvDrinkStorageService implements DrinkStorageService {

    @Override
    public void save(List<Drink> drinks) {

    }

    @Override
    public List<Drink> load() {
//        List<Person> result = new ArrayList<Person>();
//        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
//        try {
//            // Read first line
//            String line = br.readLine();
//            // Make sure file has correct headers
//            if (line==null) throw new IllegalArgumentException("File is empty");
//            if (!line.equals("name,surname,shoesize,sex"))
//                throw new IllegalArgumentException("File has wrong columns: "+line);
//            // Run through following lines
//            while ((line = br.readLine()) != null) {
//                // Break line into entries using comma
//                String[] items = line.split(",");
//                try {
//                    // If there are too many entries, throw a dummy exception, if
//                    // there are too few, the same exception will be thrown later
//                    if (items.length>4) throw new ArrayIndexOutOfBoundsException();
//                    // Convert data to person record
//                    Person person = new Person();
//                    person.setName    (                     items[0] );
//                    person.setSurname (                     items[1] );
//                    person.setShoeSize(Double .parseDouble (items[2]));
//                    person.setSex     (Boolean.parseBoolean(items[3]));
//                    result.add(person);
//                } catch (ArrayIndexOutOfBoundsException|NumberFormatException|NullPointerException e) {
//                    // Caught errors indicate a problem with data format -> Print warning and continue
//                    System.out.println("Invalid line: "+ line);
//                }
//            }
//            return result;
//        } finally {
//            br.close();
//        }
        return new ArrayList<>();
    }
}
