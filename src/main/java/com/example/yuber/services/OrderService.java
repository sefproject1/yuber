package com.example.yuber.services;

import com.example.yuber.exceptions.EmptyInputException;
import com.example.yuber.models.OrderModel;
import com.example.yuber.models.OrderSession;
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

    //private static List<OrderModel> address;

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

    public static void rideIsOver(OrderModel orderModel) {
        parseJson();
        for(OrderModel order : orders) {
            if(order.equals((orderModel)) && order.getStatus().equals("ACCEPTED")) {
                System.out.println("ok");
                order.setStatus("COMPLETED");
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
        parseJson();
    }

    public static void acceptRide(OrderModel orderModel) {
        parseJson();
        for(OrderModel order : orders) {
            if(order.equals((orderModel)) && order.getStatus().equals("WAITING")) {
                order.setStatus("ACCEPTED");
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

    public static void cancelRide(OrderModel orderModel) {
        parseJson();
        for(OrderModel order : orders) {
            if(order.equals((orderModel)) && order.getStatus().equals("ACCEPTED")) {
                order.setStatus("WAITING");
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

    public static ArrayList<String> getSourceAddress() {

        ArrayList<String> arrayList1 = new ArrayList<>();
        //String address = "";
        parseJson();
        for(OrderModel orderModel : orders) {
            arrayList1.add(orderModel.getSourceAddress());
        }
        return arrayList1;
    }

    public static ArrayList<String> getDestAddress() {
        ArrayList<String> arrayList1 = new ArrayList<>();
        //String address = "";
        parseJson();
        for(OrderModel orderModel : orders) {
            //address += orderModel.getSourceAddress();
            arrayList1.add(orderModel.getDestinationAddress());
        }
        return arrayList1;
    }

    public static ArrayList<String> getStatus() {
        ArrayList<String> arrayList1 = new ArrayList<>();
        //String address = "";
        parseJson();
        for(OrderModel orderModel : orders) {
            //address += orderModel.getSourceAddress();
            arrayList1.add(orderModel.getStatus());
            //System.out.println("order " + arrayList1);
        }
        return arrayList1;
    }

    static int size = 0;
    public static int getSize() {
        parseJson();
        return orders.size();
    }

    public static List<OrderModel> getOrder() {
        parseJson();
        return orders;
    }

    public static String findUser(OrderModel orderModel){
        parseJson();
        for(OrderModel order: orders){
            if(order.equals(orderModel))
                return order.getCustomerUsername();
        }
        return null;
    }

}
