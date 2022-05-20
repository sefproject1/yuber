package com.example.yuber.services;

import com.example.yuber.exceptions.UserAlreadyExistsException;
import com.example.yuber.models.OrderModel;
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

    public static void addOrder(String customerUsername, String sourceAddress, String destinationAddress) {
        parseJson();

        arrayList.add(new OrderModel(customerUsername, sourceAddress, destinationAddress, null, "WAITING"));
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("orders.json"), arrayList);
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
}
