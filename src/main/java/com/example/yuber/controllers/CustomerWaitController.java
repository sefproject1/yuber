package com.example.yuber.controllers;

import com.example.yuber.models.OrderSession;
import com.example.yuber.models.UserSession;
import com.example.yuber.services.AcceptanceRunnable;
import com.example.yuber.services.OrderService;
import com.example.yuber.services.SceneService;
import com.example.yuber.services.UserService;
import javafx.application.Platform;
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

    @FXML
    private Label price;

    private Thread t;

    private boolean running = true;

    @FXML
    public void initialize() throws InterruptedException, IOException {
        sourceAddress.setText("From: " + OrderSession.getOrder().getSourceAddress());
        destinationAddress.setText("To: " + OrderSession.getOrder().getDestinationAddress());
        price.setText("You'll need to pay RON " + UserService.calculatePrice(UserSession.getUser()) + " for this ride.");

        t = new Thread(this::handleThread);
        t.start();
    }

    public void handleThread() {
        do {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (OrderService.checkIfAccepted(OrderSession.getOrder())) {
                Platform.runLater(() -> {
                    try {
                        SceneService.NewScene("/com/example/yuber/accepted-view.fxml", (Stage) rootPane.getScene().getWindow(), rootPane.getScene());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            }
        } while(running);
    }

    @FXML
    protected void onCancelButtonClick() throws IOException {
        SceneService.NewScene("/com/example/yuber/customer-view.fxml", (Stage)rootPane.getScene().getWindow(), rootPane.getScene());
        running = false;
        OrderService.cancel(OrderSession.getOrder());
        OrderSession.deleteOrder();
        UserService.cancelPenalty(UserSession.getUser());
    }
}