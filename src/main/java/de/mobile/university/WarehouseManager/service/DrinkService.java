package de.mobile.university.WarehouseManager.service;

import de.mobile.university.WarehouseManager.gui.model.Drink;

import java.util.HashSet;
import java.util.Set;


public class DrinkService {

    private final String configPath;
    private Set<Drink> drinks;

    public DrinkService() {
        this.configPath = "config/";
        this.drinks = new HashSet<>();
    }

    public Set<Drink> load() {
//        Drinks drinks = new Drinks();
//        try {
//            JsonReader reader = new JsonReader(new FileReader(configPath + AppConfig.CONNECTIONS));
//            connections = gson.fromJson(reader, Connections.class);
//        } catch (IOException e) {
////            e.printStackTrace();
//        }
        if (!drinks.isEmpty()) {
            return drinks;
        }
        drinks.add(new Drink("Sprite", 3));
        return drinks;
    }

    public void saveConnections(Drink drinks) {
//        try {
//            File file = new File(configPath + AppConfig.CONNECTIONS);
//            file.getParentFile().mkdirs();
//            String connectionsData = gson.toJson(connections);
//            PrintWriter printWriter = new PrintWriter(new FileWriter(new File(configPath + AppConfig.CONNECTIONS)));
//            printWriter.print(connectionsData);
//            printWriter.close();
//        } catch (IOException | SecurityException e) {
//            e.printStackTrace();
//        }
    }

    public void updateConnection(Drink drink) {
//        List<Drink> drinkList = loadConnections().getConnections();
//        System.out.println(drinkList);
//        List<Drink> newDrinkList = loadConnections().getConnections();
//        for (Drink c : drinkList) {
//            if (c.getID().equals(drink.getID())) {
//                newDrinkList.set(drinkList.indexOf(c), drink);
//            }
//        }
//        Drinks newDrinks = new Drinks();
//        newDrinks.setConnections(newDrinkList);
//        saveConnections(newDrinks);
    }

    public void addConnection(Drink c) {
//        List<Drink> drinkList = loadConnections().getConnections();
//        drinkList.add(c);
//        saveConnections(new Drinks(drinkList));
    }

    public void removeConnection(Drink drink) {
//        List<Drink> drinkList = loadConnections().getConnections();
//        List<Drink> newDrinkList = loadConnections().getConnections();
//        for (Drink c : drinkList) {
//            if (c.getID().equals(drink.getID())) {
//                newDrinkList.remove(drinkList.indexOf(c));
//            }
//        }
//        Drinks newDrinks = new Drinks();
//        newDrinks.setConnections(newDrinkList);
//        saveConnections(newDrinks);
    }
}