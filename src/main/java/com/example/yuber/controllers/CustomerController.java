package com.example.yuber.controllers;

import com.example.yuber.exceptions.EmptyInputException;
import com.example.yuber.models.OrderSession;
import com.example.yuber.models.UserSession;
import com.example.yuber.services.OrderService;
import com.example.yuber.services.SceneService;
import com.example.yuber.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerController {
    @FXML
    private VBox rootPane;

    @FXML
    private TextField sourceAddress;

    @FXML
    private TextField destinationAddress;

    @FXML
    private Label error;

    @FXML
    protected void onPlaceOrderButtonClick() throws IOException {
        try {
            OrderService.addOrder(UserSession.getUser().getUsername(), sourceAddress.getText(), destinationAddress.getText());
            SceneService.NewScene("/com/example/yuber/customer-wait-view.fxml", (Stage) rootPane.getScene().getWindow(), rootPane.getScene());
        } catch(EmptyInputException e) {
            error.setText(e.getMessage());
        }
    }

    @FXML
    protected void onResetButtonClick() {
        sourceAddress.setText("");
        destinationAddress.setText("");
    }

    @FXML
    protected void onLogoutButtonClick() throws IOException {
        UserService.logout();
        SceneService.NewWindow("login-view.fxml", "Welcome to Yuber");

        // close current window
        Stage currStage = (Stage)sourceAddress.getScene().getWindow();
        currStage.close();
    }
}