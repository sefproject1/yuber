package com.example.yuber.controllers;

import com.example.yuber.exceptions.UserAlreadyExistsException;
import com.example.yuber.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegistrationController {
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

    ObservableList<String> test = FXCollections.observableArrayList("driver", "customer");
    @FXML
    public void initialize() {
        role.getItems().addAll(test);
    }

    @FXML
    public void handleRegistrationAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), surnameField.getText(), nameField.getText(), phoneNumberField.getText(), emailField.getText(), addressField.getText(), role.getValue());
            registrationMessage.setText("Account created successfully!");
        }catch (UserAlreadyExistsException e){
            registrationMessage.setText(e.getMessage());
        }

    }
}
