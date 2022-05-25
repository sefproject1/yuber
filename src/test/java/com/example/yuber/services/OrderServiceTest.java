package com.example.yuber.services;

import com.example.yuber.exceptions.EmptyInputException;
import com.example.yuber.exceptions.UserAlreadyExistsException;
import com.example.yuber.models.OrderModel;
import com.example.yuber.models.UserModel;
import com.example.yuber.models.UserSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private UserModel userModel;
    private OrderModel orderModel;

    @BeforeEach
    public void setUp() throws Exception {
        userModel = new UserModel("username", "password", "surname", "name", "phone_number", "email", "address", "driver", 0);
        orderModel = new OrderModel("customer", "sourceAddress", "destinationAddress", "driver", "WAITING");
    }

    @AfterEach
    public void clean() {
        try {
            FileWriter writer = new FileWriter("users.json", false);
            writer.write("[]");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("orders.json", false);
            writer.write("[]");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addOrder() throws EmptyInputException {
        OrderService.addOrder("customer", "sourceAddress", "destinationAddress");
    }

    @Test
    void checkIfAccepted() throws EmptyInputException, UserAlreadyExistsException {
        OrderService.addOrder("customer", "sourceAddress", "destinationAddress");
        UserService.addUser("username", "password", "surname", "name", "phone_number", "email", "address", "driver");
        UserSession.setUser(userModel);
        OrderService.acceptRide(orderModel);
        OrderService.checkIfAccepted(orderModel);
    }

    @Test
    void checkIfCanceled() throws EmptyInputException, UserAlreadyExistsException {
        OrderService.addOrder("customer", "sourceAddress", "destinationAddress");
        UserService.addUser("username", "password", "surname", "name", "phone_number", "email", "address", "driver");
        UserSession.setUser(userModel);
        OrderService.acceptRide(orderModel);
        OrderService.cancelRide(orderModel);
        OrderService.checkIfCanceled(orderModel);
    }

    @Test
    void checkIfFinished() throws EmptyInputException, UserAlreadyExistsException {
        OrderService.addOrder("customer", "sourceAddress", "destinationAddress");
        UserService.addUser("username", "password", "surname", "name", "phone_number", "email", "address", "driver");
        UserSession.setUser(userModel);
        OrderService.acceptRide(orderModel);
        OrderService.rideIsOver(orderModel);
        OrderService.checkIfFinished(orderModel);
    }
}