package com.example.yuber.controllers;

import com.example.yuber.Main;
import com.example.yuber.models.UserModel;
import com.example.yuber.models.UserSession;
import com.example.yuber.services.SceneService;
import com.example.yuber.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    @FXML
    private VBox rootPane;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label error;

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        try {
            UserService.login(username.getText(), password.getText());

            // close current window
            Stage currStage = (Stage)error.getScene().getWindow();
            currStage.close();
        } catch(Exception e) {
            error.setText("Invalid username and/or password.");
        }
    }

    @FXML
    protected void onRegisterButtonClick(ActionEvent event) throws IOException {
        SceneService.NewScene("/com/example/yuber/register-view.fxml", (Stage)rootPane.getScene().getWindow(), rootPane.getScene());
    }
}