package com.example.yuber.controllers;

import com.example.yuber.models.OrderSession;
import com.example.yuber.models.UserSession;
import com.example.yuber.services.OrderService;
import com.example.yuber.services.RatingService;
import com.example.yuber.services.SceneService;
import com.example.yuber.services.UserService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class AcceptedController {
    @FXML
    private VBox rootPane;

    @FXML
    private Label info;

    @FXML
    private Label driverUsername;

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
        info.setText("Please wait for the driver to pick you up and get you to the destination.");
        OrderService.updateOrderForClient(OrderSession.getOrder());
        DecimalFormat df = new DecimalFormat("0.0");
        driverUsername.setText("Driver: " + OrderSession.getOrder().getDriverUsername() + " (" + df.format(RatingService.getRating(OrderSession.getOrder().getDriverUsername())) + "/5.0 rating)");
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
            if (OrderService.checkIfCanceled(OrderSession.getOrder())) {
                Platform.runLater(() -> {
                    try {
                        SceneService.NewScene("/com/example/yuber/customer-wait-view.fxml", (Stage) rootPane.getScene().getWindow(), rootPane.getScene());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            }
            else if (OrderService.checkIfFinished(OrderSession.getOrder())) {
                Platform.runLater(() -> {
                    try {
                        SceneService.NewScene("/com/example/yuber/rating-view.fxml", (Stage) rootPane.getScene().getWindow(), rootPane.getScene());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            }
        } while(running);
    }
}