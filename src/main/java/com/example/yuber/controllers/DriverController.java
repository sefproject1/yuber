package com.example.yuber.controllers;

import com.example.yuber.services.OrderService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class DriverController {

    @FXML
    GridPane pane;
    int index;
    int row = 1;
    int rowIndex = 0;
    int size;
    int statusIndex = 0, sourceIndex = 0, destinationIndex = 0;
    boolean flag = true;

    public void initialize(){
        getSize();
        for(index = 1; index <= size; index++){
            IdLabel();
            sourceAddressLabel();
            destAddressLabel();
            statusLabel();
            System.out.println("flag" + flag);
            addButton();
            row++;
        }
    }

    public void addButton() {
        Button button = new Button("Accept ride");
        if (!flag)
            button.setVisible(false);
        //button.getStyleClass().addAll(Style.);
        pane.add(button, 4, rowIndex);
        rowIndex++;
    }

    public void IdLabel() {
        Label id = new Label("ID: " + index);
        id.setMinSize(50, 20);
        id.setFont(new Font("Arial", 20));
        pane.add(id, 0, rowIndex);
    }

    public void sourceAddressLabel() {
        Label sourceAddress = new Label("From: " + getSourceAddress());
        sourceAddress.setMinSize(50, 20);
        sourceAddress.setFont(new Font("Arial", 20));
        pane.add(sourceAddress, 1, rowIndex);
        sourceIndex++;
    }

    public void destAddressLabel() {
        Label destAddress = new Label("To: " + getDestAddress());
        destAddress.setMinSize(50, 20);
        destAddress.setFont(new Font("Arial", 20));
        pane.add(destAddress, 2, rowIndex);
        destinationIndex++;
    }

    public void statusLabel() {
        flag = true;
        Label status = new Label("Status: " + getStatus());
        if(getStatus().equals("CANCELED")){
            flag = false;
        }
        status.setMinSize(50, 20);
        status.setFont(new Font("Arial", 20));
        pane.add(status, 3, rowIndex);
        statusIndex++;
    }

    public String getSourceAddress(){
        ArrayList<String> arrayList = new ArrayList<>();
        for(String array : OrderService.getSourceAddress()){
            arrayList.add(array);
        }
        return arrayList.get(sourceIndex);
    }

    public String getDestAddress() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(String array : OrderService.getDestAddress()){
            arrayList.add(array);
        }
        return arrayList.get(statusIndex);
    }

    public String getStatus() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(String array : OrderService.getStatus()){
            arrayList.add(array);
            System.out.println(array);
        }
        return arrayList.get(statusIndex);
    }
    public void getSize() {
        size = OrderService.getSize();
    }

}
