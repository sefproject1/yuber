package com.example.yuber.controllers;

import com.example.yuber.exceptions.UserAlreadyExistsException;
import com.example.yuber.services.UserService;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegistrationController {
    public Text registrationMessage;
    public PasswordField passwordField;
    public TextField usernameField;
    public TextField nameField;
    public TextField surnameField;
    public TextField addressField;
    public TextField phoneNumberField;
    public ChoiceBox<String> roleField;

    public void initialize() {
        roleField.getItems().addAll("Customer", "Driver");
    }

    public void handleRegistrationAction(){
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), nameField.getText(), addressField.getText(), phoneNumberField.getText(), roleField.getValue(), surnameField.getText());
            registrationMessage.setText("Account created successfully!");
        }catch (UserAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
    }
}
