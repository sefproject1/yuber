package com.example.yuber;

import com.example.yuber.services.FileSystemService;
import com.example.yuber.services.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        UserService.loadUsersFromFile();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 800);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/styles.css")).toExternalForm());
        stage.setTitle("Yuber");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        String current = System.getProperty("user.dir");
        System.out.println("current dir" + current);
        Path path = Paths.get(current, ".registration");
        //System.out.println("path" + path);

        Path user_path = FileSystemService.getPathToFile("config", "user.json");
        System.out.println("user_path" + user_path);
        //System.out.println(path.resolve(Paths.get(".")));
        launch();
    }
}