package com.example.yuber.services;

import com.example.yuber.models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class LoginService {

    private static List<UserModel> users;

    public static void parseJson() {
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON file to map
            users = Arrays.asList(mapper.readValue(LoginService.class.getClassLoader().getResource("users.json"), UserModel[].class));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static UserModel checkInfo(String username, String password) {
        parseJson();

        for(UserModel user: users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }
}
