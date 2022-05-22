package com.example.yuber.controllers;

import com.example.yuber.models.OrderModel;
import com.example.yuber.models.OrderSession;
import com.example.yuber.services.OrderService;
import com.example.yuber.services.SceneService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DriverController implements Initializable {

    @FXML
    public VBox rootPane;
    public Button refresh;
    @FXML
    GridPane pane;
    int index = 1;
    int row = 1;
    int rowIndex = 0;
    //int size;
    int statusIndex = 0, sourceIndex = 0, destinationIndex = 0;
    boolean canceled = false, completed = false;

    public void initialize(URL location, ResourceBundle resources){
        List<OrderModel> orders = OrderService.getOrder();
        //int size = OrderService.getSize();

        for(OrderModel order : orders){
            IdLabel();
            sourceAddressLabel();
            destAddressLabel();
            statusLabel();
            addButton(order);
            row++;
        }
    }

    public void addButton(OrderModel orderModel) {
        Button button = new Button("Accept ride");
        button.getStyleClass().add("Button");
        if (canceled)
            button.setVisible(false);
        if(completed)
            button.setVisible(false);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    OrderSession.setOrder(orderModel);
                    OrderService.acceptRide(OrderSession.getOrder());
                    SceneService.NewScene("/com/example/yuber/driver-wait-view.fxml", (Stage) rootPane.getScene().getWindow(), rootPane.getScene());
                    //SceneService.NewWindow("driver-wait-view.fxml", "Welcome to Yuber");

                    // close current window
                    //Stage currStage = (Stage)rootPane.getScene().getWindow();
                    //currStage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pane.add(button, 4, rowIndex);
        rowIndex++;
    }

    public void IdLabel() {
        Label id = new Label("ID: " + index);
        id.setMinSize(50, 20);
        id.setFont(new Font("Arial", 18));
        pane.add(id, 0, rowIndex);
        index++;
    }

    public void sourceAddressLabel() {
        Label sourceAddress = new Label("From: " + getSourceAddress());
        sourceAddress.setMinSize(50, 20);
        sourceAddress.setFont(new Font("Arial", 18));
        pane.add(sourceAddress, 1, rowIndex);
        sourceIndex++;
    }

    public void destAddressLabel() {
        Label destAddress = new Label("To: " + getDestAddress());
        destAddress.setMinSize(50, 20);
        destAddress.setFont(new Font("Arial", 18));
        pane.add(destAddress, 2, rowIndex);
        destinationIndex++;
    }

    public void statusLabel() {
        canceled = false;
        completed = false;
        Label status = new Label("Status: " + getStatus());
        if(getStatus().equals("CANCELED")){
            canceled = true;
        }
        if(getStatus().equals("COMPLETED")){
            completed = true;
        }
        status.setMinSize(50, 20);
        status.setFont(new Font("Arial", 18));
        pane.add(status, 3, rowIndex);
        statusIndex++;
    }

    public String getSourceAddress(){
        ArrayList<String> arrayList = new ArrayList<>(OrderService.getSourceAddress());
        return arrayList.get(sourceIndex);
    }

    public String getDestAddress() {
        ArrayList<String> arrayList = new ArrayList<>(OrderService.getDestAddress());
        return arrayList.get(statusIndex);
    }

    public String getStatus() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(String array : OrderService.getStatus()){
            arrayList.add(array);
        }
        return arrayList.get(statusIndex);
    }


    public void refresh(ActionEvent actionEvent) throws IOException {
        SceneService.NewScene("/com/example/yuber/driver-view.fxml", (Stage)rootPane.getScene().getWindow(), rootPane.getScene());
    }
}
