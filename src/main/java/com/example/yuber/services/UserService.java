package com.example.yuber.services;


import com.example.yuber.exceptions.InvalidCredentialsException;
import com.example.yuber.exceptions.UserAlreadyExistsException;
import com.example.yuber.models.OrderModel;
import com.example.yuber.models.UserModel;
import com.example.yuber.models.UserSession;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserService {
    private static List<UserModel> users;
    private static List<UserModel> arrayList;

    public static void parseJson(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            users = Arrays.asList(objectMapper.readValue(Path.of("users.json").toFile(), UserModel[].class));
            arrayList = new ArrayList<>(users);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addUser(String username, String password, String surname, String name, String phone_number, String email, String address, String role) throws UserAlreadyExistsException {
        checkUserDoesNotExist(username);
        arrayList.add(new UserModel(username, encodePassword(username, password), surname, name, phone_number, email, address, role, 0));
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("users.json"), arrayList);
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    private static void checkUserDoesNotExist(String username) throws UserAlreadyExistsException{
        for (UserModel user : arrayList) {
            if(Objects.equals(username, user.getUsername()))
                throw new UserAlreadyExistsException(username);
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest messageDigest = getMessageDigest();
        messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.UTF_8).replace("\"", "");
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        }catch (NoSuchAlgorithmException e){
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return messageDigest;
    }

    public static void login(String username, String password) throws InvalidCredentialsException {
        // check if the user exists in the file
        UserModel user = UserService.checkInfo(username, password);
        if (user != null) {
            // save the user to the UserSession singleton
            UserSession.getInstance().setUser(user);

            try {
                String view = "";
                if(UserSession.getInstance().getUser().getRole().equals("driver"))
                    view = "driver-view.fxml";
                else view = "customer-view.fxml";

                SceneService.NewWindow(view, "Dashboard - Yuber");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        else throw new InvalidCredentialsException();
    }

    public static UserModel checkInfo(String username, String password) {
        parseJson();

        for(UserModel user: users) {
            if (user.getUsername().equals(username)) {
                String encodedPassword = UserService.encodePassword(username, password);
                if (user.getPassword().equals(encodedPassword))
                    return user;
                return null;
            }
        }
        return null;
    }

    public static void logout() {
        UserSession.getInstance().deleteUser();
    }

    public static void cancelPenalty(UserModel um) {
        parseJson();

        for (UserModel user: users) {
            if (user.equals(um)) {
                user.setPenalty(um.getPenalty()+10);
                UserSession.setUser(user);
                break;
            }
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("users.json"), arrayList);
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    public static double calculatePrice(UserModel um) {
        return 25 + (um.getPenalty() * 25) / 100;
    }

}
