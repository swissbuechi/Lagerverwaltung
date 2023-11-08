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
        createDrinkList();
        createTotalQuantityLabel();
        createAddButton();
        createExitButton();
        setAlignment(Pos.CENTER);
        setVgap(10);
        setHgap(10);
    }

    private void createDrinkList() {
        TableView<Getraenk> tableView = new TableView<>();
        tableView.setPrefWidth(1100);
        tableView.setPrefHeight(500);
        tableView.setPlaceholder(new Label("Keine Getraenke vorhanden"));

        TableColumn<Getraenk, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Set column to take the available width
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Getraenk, Integer> quantityCol = new TableColumn<>("Anzahl");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("anzahl"));

        TableColumn<Getraenk, Void> changeCol = new TableColumn<>("Bestandsaenderung (+/-)");
        changeCol.setCellFactory(col -> new TableCell<>() {
            private final TextField textField = new TextField();

            {
                textField.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.ENTER) {
                        int changeValue = Integer.parseInt(textField.getText());
                        Getraenk getraenk = getTableView().getItems().get(getIndex());
                        getraenkeVerwaltungService.updateQuantity(getraenk.getName(), changeValue);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(textField);
                    textField.setText(""); // Clear the text field for reuse
                }
            }
        });

        // Add columns to table
        tableView.getColumns().add(nameCol);
        tableView.getColumns().add(quantityCol);
        tableView.getColumns().add(changeCol);

        // Disable sorting
        nameCol.setSortable(false);
        quantityCol.setSortable(false);
        changeCol.setSortable(false);

        // Disable reordering
        nameCol.setReorderable(false);
        quantityCol.setReorderable(false);
        changeCol.setReorderable(false);

        // Disable selection
        tableView.setSelectionModel(null);
        tableView.setItems(getraenkeVerwaltungService.getDrinks());

        add(tableView, 0, 0, 2, 1);
    }

    private void createTotalQuantityLabel() {
        int totalQuantity = calculateTotalQuantity(getraenkeVerwaltungService.getDrinks());
        Label totalLabel = new Label("Gesamtsumme: " + totalQuantity);

        // Add a listener to update the label when the underlying list changes
        getraenkeVerwaltungService.getDrinks().addListener((ListChangeListener<Getraenk>) c -> {
            while (c.next()) {
                if (c.wasUpdated() || c.wasAdded() || c.wasRemoved()) {
                    int newTotalQuantity = calculateTotalQuantity(
                            getraenkeVerwaltungService.getDrinks());
                    totalLabel.setText("Gesamtsumme: " + newTotalQuantity);
                }
            }
        });

        // Set the alignment of the label to center
        setHalignment(totalLabel, javafx.geometry.HPos.CENTER);
        add(totalLabel, 0, 1, 2, 1);
    }

    private void createExitButton() {
        Button exitButton = new Button("Beenden");
        exitButton.setOnAction(e -> System.exit(0));
        add(exitButton, 0, 2);
    }

    private void createAddButton() {
        Button addDrinkButton = new Button("Getraenk hinzufuegen");
        setHalignment(addDrinkButton, javafx.geometry.HPos.RIGHT);
        addDrinkButton.setOnAction(e -> getraenHinzufuegenView.showAddDrinkDialog());
        add(addDrinkButton, 1, 2);
    }

    private int calculateTotalQuantity(List<Getraenk> getraenke) {
        return getraenke.stream().mapToInt(Getraenk::getAnzahl).sum();
    }
}
