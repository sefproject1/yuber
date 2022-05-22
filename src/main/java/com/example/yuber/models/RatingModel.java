package com.example.yuber.models;

public class RatingModel {
    private String username;
    private double rating;

    public RatingModel() {
    }

    public RatingModel(String username, double rating) {
        this.username = username;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
