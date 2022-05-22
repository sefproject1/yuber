package com.example.yuber.services;


import com.example.yuber.exceptions.InvalidCredentialsException;
import com.example.yuber.exceptions.UserAlreadyExistsException;
import com.example.yuber.models.OrderModel;
import com.example.yuber.models.RatingModel;
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

public class RatingService {
    private static List<RatingModel> ratings;
    private static List<RatingModel> arrayList;

    public static void parseJson(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ratings = Arrays.asList(objectMapper.readValue(Path.of("ratings.json").toFile(), RatingModel[].class));
            arrayList = new ArrayList<>(ratings);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addRating(String username, double rating) {
        parseJson();
        arrayList.add(new RatingModel(username, rating));
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("ratings.json"), arrayList);
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    public static double getRating(String driverUsername) {
        parseJson();
        double rating = 0;
        int ratingCounter = 0;
        for (RatingModel ratingModel : ratings) {
            if (ratingModel.getUsername().equals(driverUsername)) {
                rating += ratingModel.getRating();
                ratingCounter++;
            }
        }

        if (ratingCounter == 0) return 5;
        return rating/ratingCounter;
    }
}
