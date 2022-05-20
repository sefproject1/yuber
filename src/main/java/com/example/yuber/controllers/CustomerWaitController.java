package com.example.yuber.controllers;

import com.example.yuber.models.UserSession;
import com.example.yuber.services.OrderService;
import com.example.yuber.services.SceneService;
import com.example.yuber.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerWaitController {
    @FXML
    private VBox rootPane;

    @FXML
    private TextField sourceAddress;

    @FXML
    private TextField destinationAddress;

    @FXML
    protected void onCancelButtonClick() throws IOException {
        SceneService.NewScene("/com/example/yuber/customer-view.fxml", (Stage)rootPane.getScene().getWindow(), rootPane.getScene());
    }
}