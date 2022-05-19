package com.example.yuber.models;

public final class UserSession { // Singleton
    private static UserSession instance = new UserSession();
    private static UserModel user;

    private UserSession() {}

    public static UserSession getInstance() {
        return instance;
    }

    public static void setUser(UserModel user) {
        UserSession.user = user;
    }

    public static UserModel getUser() {
        return user;
    }
}
