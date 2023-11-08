package de.mobile.university.Lagerverwaltung.gui.view;

import de.mobile.university.Lagerverwaltung.model.Getraenk;
import de.mobile.university.Lagerverwaltung.service.GetraenkeVerwaltungService;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.util.List;

public class GetraenkeUebersichtView extends GridPane {

    private final GetraenkeVerwaltungService getraenkeVerwaltungService;
    private final GetraenHinzufuegenView getraenHinzufuegenView;

    public GetraenkeUebersichtView() {
        getraenkeVerwaltungService = GetraenkeVerwaltungService.INSTANCE.getInstance();
        getraenHinzufuegenView = new GetraenHinzufuegenView();

        // Erstellen der GUI-Elemente
        erstelleGetraenkeUbersicht();
        erstelleGesamtsummeLabel();
        erstelleGetraenkHinzufuegenButton();
        erstelleBeendenButton();

        // Positionierung
        setAlignment(Pos.CENTER);
        setVgap(10);
        setHgap(10);
    }

    private void erstelleGetraenkeUbersicht() {
        TableView<Getraenk> tableView = new TableView<>();
        tableView.setPrefWidth(1100);
        tableView.setPrefHeight(500);
        tableView.setPlaceholder(new Label("Keine Getraenke vorhanden"));

        // Erstellen der "Name"-Spalte
        TableColumn<Getraenk, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Tabelle soll ganze breite verwenden
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Erstellen der "Anzahl"-Spalte
        TableColumn<Getraenk, Integer> quantityCol = new TableColumn<>("Anzahl");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("anzahl"));

        // Erstellen der "Bestandsaenderung"-Spalte
        TableColumn<Getraenk, Void> changeCol =
                new TableColumn<>("Bestandsaenderung " + "(+/-)");
        changeCol.setCellFactory(col -> {
            return new TableCell<>() {
                // Textfeld, in dem die Bestandsaenderung eingegeben werden kann
                private final TextField textField = new TextField();

                {
                    textField.setOnKeyPressed(e -> {
                        if (e.getCode() == KeyCode.ENTER) {
                            int changeValue = Integer.parseInt(textField.getText());
                            Getraenk getraenk = getTableView().getItems().get(getIndex());
                            getraenkeVerwaltungService.bestandesaenderung(
                                    getraenk.getName(), changeValue);
                        }
                    });
                }

                // Ueberschreiben der updateItem-Methode, um das Textfeld in
                // der Zelle
                // anzuzeigen
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(textField);
                        textField.setText(""); // Zuruecksetzen des Textfelds
                    }
                }
            };
        });

        // Spalten zur Tabelle hinzufuegen
        tableView.getColumns().add(nameCol);
        tableView.getColumns().add(quantityCol);
        tableView.getColumns().add(changeCol);

        // Sortierung deaktivieren
        nameCol.setSortable(false);
        quantityCol.setSortable(false);
        changeCol.setSortable(false);

        // Umordnen der Spalten deaktivieren
        nameCol.setReorderable(false);
        quantityCol.setReorderable(false);
        changeCol.setReorderable(false);

        // Anklicken von Zellen deaktivieren
        tableView.setSelectionModel(null);
        tableView.setItems(getraenkeVerwaltungService.getGetraenke());

        // Hinzufuegen der Tabelle zum GridPane
        add(tableView, 0, 0, 2, 1);
    }

    // Berechnen der Gesamtsumme aller Getraenke
    private int berechneGesamtsumme(List<Getraenk> getraenke) {
        return getraenke.stream().mapToInt(Getraenk::getAnzahl).sum();
    }

    private void erstelleGesamtsummeLabel() {
        int totalQuantity =
                berechneGesamtsumme(getraenkeVerwaltungService.getGetraenke());
        Label totalLabel = new Label("Gesamtsumme: " + totalQuantity);

        // Hinzufuegen eines Listeners zur Aktualisierung der Beschriftung,
        // wenn sich die zugrunde liegende Liste aendert
        getraenkeVerwaltungService.getGetraenke()
                .addListener((ListChangeListener<Getraenk>) c -> {
                    while (c.next()) {
                        if (c.wasUpdated() || c.wasAdded() || c.wasRemoved()) {
                            int newTotalQuantity = berechneGesamtsumme(
                                    getraenkeVerwaltungService.getGetraenke());
                            totalLabel.setText("Gesamtsumme: " + newTotalQuantity);
                        }
                    }
                });

        // Positionierung vom Label
        setHalignment(totalLabel, javafx.geometry.HPos.CENTER);
        add(totalLabel, 0, 1, 2, 1);
    }
    // Beenden des Programms via Button

    private void erstelleBeendenButton() {
        Button exitButton = new Button("Beenden");
        exitButton.setOnAction(e -> System.exit(0)); // Beenden des Programms
        add(exitButton, 0, 2);
    }
    // Oeffnen des Dialogs zum Hinzufuegen eines Getraenks

    private void erstelleGetraenkHinzufuegenButton() {
        Button addDrinkButton = new Button("Getraenk hinzufuegen");
        setHalignment(addDrinkButton, javafx.geometry.HPos.RIGHT);
        addDrinkButton.setOnAction(e -> getraenHinzufuegenView.showAddDrinkDialog());
        add(addDrinkButton, 1, 2);
    }
}
