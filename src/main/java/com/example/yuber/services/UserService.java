package com.example.yuber.services;


import com.example.yuber.exceptions.UserAlreadyExistsException;
import com.example.yuber.models.UserModel;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
            users = Arrays.asList(objectMapper.readValue(UserService.class.getClassLoader().getResource("users.json"), UserModel[].class));
            arrayList = new ArrayList<>(users);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addUser(String username, String password, String surname, String name, String phone_number, String email, String address, String role) throws UserAlreadyExistsException {
        checkUserDoesNotExist(username);
        arrayList.add(new UserModel(username, encodePassword(username, password), surname, name, phone_number, email, address, role));
        System.out.println(arrayList);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("userss.json"), arrayList);
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
}
