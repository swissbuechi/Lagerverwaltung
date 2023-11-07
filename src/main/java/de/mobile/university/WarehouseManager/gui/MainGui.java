package de.mobile.university.WarehouseManager.gui;

import de.mobile.university.WarehouseManager.config.AppConfig;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainGui extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            buildMainView(primaryStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void show() {
        launch();
    }

    private void buildMainView(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
        primaryStage.setTitle(AppConfig.APP_NAME);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(300);
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("images/logo.png").toString()));
        primaryStage.setOnCloseRequest(t -> System.exit(0));
        Scene scene = new Scene(root, 1200, 600);
        scene.getStylesheets().add("css/stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
