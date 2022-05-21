package com.example.yuber.controllers;

import com.example.yuber.models.OrderModel;
import com.example.yuber.services.OrderService;
import com.example.yuber.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DriverController {

    int index;
    int row = 1;
    @FXML
    GridPane button_grid;
    @FXML
    GridPane label;
    @FXML
    Button button;
    @FXML
    GridPane pane;

    int rowIndex = 0;
    public void initialize(){
        for(index = 1; index < 4; index++){
            IdLabel();
            sourceAddressLabel();
            destAddressLabel();
            statusLabel();
            addButton();
            row++;
        }
        method();
    }

    public void addButton() {
        Button button1 = new Button("Accept ride");
        pane.add(button1, 4, rowIndex);
        rowIndex++;
    }

    public void IdLabel() {
        Label id = new Label("ID: " + index);
        id.setMinSize(50, 20);
        id.setFont(new Font("Arial", 20));
        pane.add(id, 0, rowIndex);
    }

    public void sourceAddressLabel() {
        Label sourceAddress = new Label("Source address: ");
        sourceAddress.setMinSize(50, 20);
        sourceAddress.setFont(new Font("Arial", 20));
        pane.add(sourceAddress, 1, rowIndex);
    }

    public void destAddressLabel() {
        Label destAddress = new Label("Destination address: ");
        destAddress.setMinSize(50, 20);
        destAddress.setFont(new Font("Arial", 20));
        pane.add(destAddress, 2, rowIndex);
    }

    public void statusLabel() {
        Label status = new Label("Status: ");
        status.setMinSize(50, 20);
        status.setFont(new Font("Arial", 20));
        pane.add(status, 3, rowIndex);
    }

    public void method(){
        //OrderService.getOrders();
        String address = OrderService.getOrders();
        System.out.println(address);
    }

}
