package com.example.yuber.services;

import com.example.yuber.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneService {
    public static void NewWindow(String view, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(view));
        Parent root = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle(title);

        Scene scene = new Scene(root, 1100, 800);
        scene.getStylesheets().add(Main.class.getResource("css/styles.css").toExternalForm());
        stage.setScene(scene);

        stage.show();
    }

    public static void NewScene(String view, Stage parent, Scene currScene) throws IOException {
        Parent pane = FXMLLoader.load(Main.class.getResource(view));
//        Scene scene = new Scene(pane, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        Scene scene = new Scene(pane, currScene.getWidth(), currScene.getHeight());
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/com/example/yuber/css/styles.css")).toExternalForm());
        parent.setTitle("Welcome to Yuber");
        parent.setScene(scene);
    }
}
