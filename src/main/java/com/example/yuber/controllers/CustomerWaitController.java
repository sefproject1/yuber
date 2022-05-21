package com.example.yuber.controllers;

import com.example.yuber.models.OrderSession;
import com.example.yuber.models.UserSession;
import com.example.yuber.services.AcceptanceRunnable;
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
import java.nio.channels.AcceptPendingException;

public class CustomerWaitController {
    @FXML
    private VBox rootPane;

    @FXML
    private Label sourceAddress;

    @FXML
    private Label destinationAddress;

    private AcceptanceRunnable acceptanceRunnable;

    @FXML
    public void initialize() throws InterruptedException {
        sourceAddress.setText("From: " + OrderSession.getOrder().getSourceAddress());
        destinationAddress.setText("To: " + OrderSession.getOrder().getDestinationAddress());

        acceptanceRunnable = new AcceptanceRunnable();
        Thread t = new Thread(acceptanceRunnable);
        t.start();
    }

    @FXML
    protected void onCancelButtonClick() throws IOException {
        SceneService.NewScene("/com/example/yuber/customer-view.fxml", (Stage)rootPane.getScene().getWindow(), rootPane.getScene());
        acceptanceRunnable.setRunning(false);
        OrderService.cancel(OrderSession.getOrder());
        OrderSession.deleteOrder();
        UserService.cancelPenalty(UserSession.getUser());
    }
}