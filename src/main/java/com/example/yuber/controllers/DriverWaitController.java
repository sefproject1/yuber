package com.example.yuber.controllers;

import com.example.yuber.models.OrderModel;
import com.example.yuber.models.OrderSession;
import com.example.yuber.services.AcceptanceRunnable;
import com.example.yuber.services.OrderService;
import com.example.yuber.services.SceneService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DriverWaitController {

    @FXML
    private VBox rootPane;

    @FXML
    private Label sourceAddress;

    @FXML
    private Label destinationAddress;

    @FXML
    private Label rideCost;


    public void initialize() {
        sourceAddress.setText("From " + OrderSession.getOrder().getSourceAddress());
        destinationAddress.setText("To " + OrderSession.getOrder().getDestinationAddress());
        rideCost.setText("Price - ");

    }
    public void onCancelButtonClick() throws IOException {
        OrderService.cancelRide(OrderSession.getOrder());
        SceneService.NewScene("/com/example/yuber/driver-view.fxml", (Stage)rootPane.getScene().getWindow(), rootPane.getScene());

    }

    public void onFinishButtonClick() throws IOException {
        OrderService.rideIsOver(OrderSession.getOrder());
        SceneService.NewScene("/com/example/yuber/driver-view.fxml", (Stage)rootPane.getScene().getWindow(), rootPane.getScene());
    }

    public void rideCost(){

    }
}
