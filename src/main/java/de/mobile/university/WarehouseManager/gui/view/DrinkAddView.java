package de.mobile.university.WarehouseManager.gui.view;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DrinkAddView {

    private TextField tfConnectionName;
    private TextField tfUserPrinzipalName;
    private CheckBox checkBoxIsActive;
    private Button buttonSaveChange;

    //    private void createAndPlaceChangeForm() {
//        createFormFieldConnectioName();
//        createFormFieldUserPrinzipalName();
//        createFormFieldIsActive();
//
//        createFormButtonSave();
//        createFormButtonCancel();
//        createFormButtonAdd();
//        createFormButtonDel();
//    }


    private void createFormFieldConnectioName() {
//        Label lblConnectionName = new Label("Name");
//        tfConnectionName = new TextField();
//        add(lblConnectionName, 1, 1);
//        add(tfConnectionName, 2, 1, 2, 1);
    }

    private void createFormFieldUserPrinzipalName() {
//        Label lblUserPrinzipalName = new Label("Username");
//        tfUserPrinzipalName = new TextField();
//        add(lblUserPrinzipalName, 1, 2);
//        add(tfUserPrinzipalName, 2, 2, 2, 1);
    }


    private void createFormFieldIsActive() {
//        Label lblIsActive = new Label("Active");
//        checkBoxIsActive = new CheckBox();
//        add(lblIsActive, 1, 4);
//        add(checkBoxIsActive, 2, 4, 2, 1);
    }

    /**
     * Erstellt den Save Button und platziert ihn auf dem Pane
     */
    private void createFormButtonSave() {
//        buttonSaveChange = new Button("Save");
//        buttonSaveChange.setOnAction(e -> save());
//        add(buttonSaveChange, 2, 5);
    }

    /**
     * Erstellt den Abbrechen Button und platziert ihn auf dem Pane
     */
    private void createFormButtonCancel() {
//        Button buttonCancel = new Button("Cancel");
//        buttonCancel.setOnAction(e -> cancel());
//        add(buttonCancel, 3, 5);
    }

    private void createFormButtonAdd() {
//        buttonSaveChange = new Button("Add");
//        buttonSaveChange.setOnAction(e -> add());
//        add(buttonSaveChange, 4, 5);
    }

    private void createFormButtonDel() {
//        buttonSaveChange = new Button("Delete");
//        buttonSaveChange.setOnAction(e -> delete());
//        add(buttonSaveChange, 5, 5);
    }


    private void change(ObservableValue obs, Object oldSelection, Object newSelection) {
//        Drink c = (Drink) obs.getValue();
//
//        if (c != null) {
//            selectedID = c.getID();
//
//            tfConnectionName.setText(c.getName());
//            tfUserPrinzipalName.setText(c.getUserPrinzipalName());
//            checkBoxIsActive.setSelected(c.isStatus());
//        } else {
//            cancel();
//        }
    }

    private void save() {
//        String connectionName = tfConnectionName.getText();
//        String userPrinzipalName = tfUserPrinzipalName.getText();
//
//        if (selectedID != null
//                && connectionName != null && connectionName.length() > 0
//                && userPrinzipalName != null && userPrinzipalName.length() > 0) {
//            fileService.updateConnection(new Drink(selectedID, connectionName, userPrinzipalName, checkBoxIsActive.isSelected()));
//
//            List<Drink> drinkList = fileService.loadConnections().getConnections();
//            drinkObservableList.clear();
//            drinkObservableList.addAll(drinkList);
//        } else {
//            System.err.println("No valid input");
//        }
    }


    private void add() {
//        String connectionName = tfConnectionName.getText();
//        String userPrinzipalName = tfUserPrinzipalName.getText();
//
//        if (selectedID == null
//                && connectionName != null && connectionName.length() > 0
//                && userPrinzipalName != null && userPrinzipalName.length() > 0) {
//            fileService.addConnection(new Drink(connectionName, userPrinzipalName, checkBoxIsActive.isSelected()));
//
//            List<Drink> drinkList = fileService.loadConnections().getConnections();
//            drinkObservableList.clear();
//            drinkObservableList.addAll(drinkList);
//        } else {
//            System.err.println("No valid input");
//        }
    }

    private void delete() {
//        String connectionName = tfConnectionName.getText();
//        String userPrinzipalName = tfUserPrinzipalName.getText();
//
//        if (selectedID != null
//                && connectionName != null && connectionName.length() > 0
//                && userPrinzipalName != null && userPrinzipalName.length() > 0) {
//            fileService.removeConnection(new Drink(selectedID, connectionName, userPrinzipalName, checkBoxIsActive.isSelected()));
//
//            List<Drink> drinkList = fileService.loadConnections().getConnections();
//            drinkObservableList.clear();
//            drinkObservableList.addAll(drinkList);
//        } else {
//            System.err.println("No valid input");
//        }
    }

    private void cancel() {
//        tableView.getSelectionModel().clearSelection();
//        selectedID = null;
//        tfConnectionName.setText("");
//        tfUserPrinzipalName.setText("");
//        checkBoxIsActive.setSelected(false);
    }

}
