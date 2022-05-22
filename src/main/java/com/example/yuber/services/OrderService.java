package com.example.yuber.services;

import com.example.yuber.exceptions.EmptyInputException;
import com.example.yuber.exceptions.UserAlreadyExistsException;
import com.example.yuber.models.OrderModel;
import com.example.yuber.models.OrderSession;
import com.example.yuber.models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderService {
    private static List<OrderModel> orders;
    private static List<OrderModel> arrayList;

    public static void parseJson(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            orders = Arrays.asList(objectMapper.readValue(Path.of("orders.json").toFile(), OrderModel[].class));
            arrayList = new ArrayList<>(orders);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addOrder(String customerUsername, String sourceAddress, String destinationAddress) throws EmptyInputException {
        if (sourceAddress.equals("") || destinationAddress.equals("")) {
            throw new EmptyInputException("Source and destination address should not be empty!");
        }

        parseJson();

        OrderModel om = new OrderModel(customerUsername, sourceAddress, destinationAddress, null, "WAITING");
        OrderSession.setOrder(om);

        arrayList.add(om);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("orders.json"), arrayList);
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    public static boolean checkIfAccepted(OrderModel om) {
        parseJson();

        for (OrderModel order : orders) {
            if (order.equals(om) && order.getStatus().equals("ACCEPTED"))
                return true;
        }
        return false;
    }

    public static boolean checkIfCanceled(OrderModel om) {
        parseJson();

        for (OrderModel order : orders) {
            if (order.equals(om) && order.getStatus().equals("WAITING"))
                return true;
        }
        return false;
    }

    public static boolean checkIfFinished(OrderModel om) {
        parseJson();

        for (OrderModel order : orders) {
            if (order.equals(om) && order.getStatus().equals("FINISHED"))
                return true;
        }
        return false;
    }

    public static void cancel(OrderModel om) {
        parseJson();

        for (OrderModel order : orders) {
            if(order.equals(om) && order.getStatus().equals("WAITING")) {
                order.setStatus("CANCELED");
                OrderSession.setOrder(order);
                break;
            }
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("orders.json"), arrayList);
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
}
