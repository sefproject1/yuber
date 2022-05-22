package com.example.yuber.controllers;

import com.example.yuber.exceptions.EmptyInputException;
import com.example.yuber.exceptions.UserAlreadyExistsException;
import com.example.yuber.services.SceneService;
import com.example.yuber.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {
    @FXML
    private VBox rootPane;
    @FXML
    public Text registrationMessage;
    @FXML
    public TextField usernameField;
    @FXML
    public TextField passwordField;
    @FXML
    public TextField surnameField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField phoneNumberField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField addressField;
    @FXML
    public ChoiceBox<String> role;

    ObservableList<String> roles = FXCollections.observableArrayList("driver", "customer");
    @FXML
    public void initialize() {
        role.getItems().addAll(roles);
    }

    @FXML
    public void handleRegistrationAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), surnameField.getText(), nameField.getText(), phoneNumberField.getText(), emailField.getText(), addressField.getText(), role.getValue());
            registrationMessage.setText("Account created successfully!");
        }catch (UserAlreadyExistsException e){
            registrationMessage.setText(e.getMessage());
        }
        catch(EmptyInputException e) {
            registrationMessage.setText(e.getMessage());
        }

    }

    @FXML
    public void onLoginButtonClick() throws IOException {
        SceneService.NewScene("/com/example/yuber/login-view.fxml", (Stage)rootPane.getScene().getWindow(), rootPane.getScene());
    }
}
