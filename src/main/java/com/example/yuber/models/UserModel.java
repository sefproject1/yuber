package com.example.yuber.models;

public class UserModel {
    private String username;
    private String password;
    private String role;

    public UserModel() {}

    public UserModel(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean equals(Object o) {
        if (o instanceof UserModel)
            return ((UserModel)o).getUsername().equals(this.getUsername());
        return false;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
