package com.example.yuber.services;

import com.example.yuber.models.UserModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RatingServiceTest {
    UserModel userModel;

    @BeforeEach
    public void setUp() throws Exception {
        userModel = new UserModel("username", "password", "surname", "name", "phone_number", "email", "address", "driver", 0);
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
            FileWriter writer = new FileWriter("ratings.json", false);
            writer.write("[]");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addRating() {
        RatingService.addRating("username", 5.0);
    }

    @Test
    void getRating() {
        assertEquals(5.0, RatingService.getRating("username"));

        RatingService.addRating("username", 5.0);
        RatingService.addRating("username", 4.0);
        RatingService.addRating("username", 3.0);
        RatingService.addRating("username", 2.0);
        RatingService.addRating("username", 1.0);
        assertEquals(3.0, RatingService.getRating("username"));
    }
}