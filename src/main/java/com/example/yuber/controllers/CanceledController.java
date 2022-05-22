package com.example.yuber.controllers;

import com.example.yuber.models.OrderSession;
import com.example.yuber.models.UserSession;
import com.example.yuber.services.OrderService;
import com.example.yuber.services.SceneService;
import com.example.yuber.services.UserService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CanceledController {
    @FXML
    private VBox rootPane;

    @FXML
    public void onPlaceOrderButtonClick() throws IOException {
        SceneService.NewScene("/com/example/yuber/customer-view.fxml", (Stage) rootPane.getScene().getWindow(), rootPane.getScene());
    }

    public void onLogoutButtonClick() throws IOException {
        UserService.logout();
        OrderSession.deleteOrder();
        SceneService.NewWindow("login-view.fxml", "Welcome to Yuber");

        // close current window
        Stage currStage = (Stage)rootPane.getScene().getWindow();
        currStage.close();
    }
}