package com.example.yuber.services;

import com.example.yuber.exceptions.EmptyInputException;
import com.example.yuber.exceptions.InvalidCredentialsException;
import com.example.yuber.exceptions.UserAlreadyExistsException;
import com.example.yuber.models.UserModel;
import com.example.yuber.models.UserSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserModel userModel;

    @BeforeEach
    public void setUp() throws Exception {
        userModel = new UserModel("username", "password", "surname", "name", "phone_number", "email", "address", "customer", 0);
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
    }

    @Test
    void addUserValid() throws EmptyInputException, UserAlreadyExistsException {
        UserService.addUser("username", "password", "surname", "name", "phone_number", "email", "address", "customer");
    }

    @Test
    void addUserInvalid() throws EmptyInputException, UserAlreadyExistsException {
        UserService.addUser("username", "password", "surname", "name", "phone_number", "email", "address", "customer");

        try {
            UserService.addUser("username", "password", "surname", "name", "phone_number", "email", "address", "customer");
            fail("UserAlreadyExists exception didn't occur.");
        } catch(UserAlreadyExistsException e) {
        }
    }

    @Test
    void loginValid() throws EmptyInputException, UserAlreadyExistsException, InvalidCredentialsException {
        UserService.addUser("username", "password", "surname", "name", "phone_number", "email", "address", "customer");
        UserService.login("username", "password");
    }

    @Test
    void loginInvalid() {
        try {
            UserService.login("username", "password");
            fail("InvalidCredentialsException didn't occur.");
        } catch(InvalidCredentialsException e) {

        }
    }

    @Test
    void logout() throws InvalidCredentialsException, EmptyInputException, UserAlreadyExistsException {
        UserService.addUser("username", "password", "surname", "name", "phone_number", "email", "address", "customer");
        UserService.login("username", "password");
        UserService.logout();
    }

    @Test
    void cancelPenalty() throws Exception {
        UserService.addUser("username", "password", "surname", "name", "phone_number", "email", "address", "customer");
        UserService.login("username", "password");

        UserService.cancelPenalty(UserSession.getUser());
        assertEquals(27.0, UserService.calculatePrice(UserSession.getUser()));
    }

    @Test
    void calculatePrice() throws Exception {
        userModel.setPenalty(0);
        assertEquals(25.0, UserService.calculatePrice(userModel));

        userModel.setPenalty(10);
        assertEquals(27.0, UserService.calculatePrice(userModel));

        userModel.setPenalty(20);
        assertEquals(30.0, UserService.calculatePrice(userModel));

        userModel.setPenalty(30);
        assertEquals(32.0, UserService.calculatePrice(userModel));

        userModel.setPenalty(50);
        assertEquals(37.0, UserService.calculatePrice(userModel));

        userModel.setPenalty(75);
        assertEquals(43.0, UserService.calculatePrice(userModel));

        userModel.setPenalty(100);
        assertEquals(50.0, UserService.calculatePrice(userModel));

        userModel.setPenalty(150);
        assertEquals(62.0, UserService.calculatePrice(userModel));
    }
}