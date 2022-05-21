package com.example.yuber;

import com.example.yuber.services.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        UserService.parseJson();
        //System.out.println("ok");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("driver-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 800);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/styles.css")).toExternalForm());
        stage.setTitle("Welcome to Yuber");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}