package com.example.yuber.models;

public class User {
    private String username;
    private String password;
    private String role;
    private String name;
    private String surname;
    private String phone_number;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }

    public User(String username, String name, String surname, String password, String address, String phone_number, String role){
        this.address = address;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.phone_number = phone_number;
        this. role = role;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if(!username.equals(user.username)) return false;
        if(!password.equals(user.password)) return false;
        if(!name.equals(user.name)) return false;
        if(!surname.equals(user.surname)) return false;
        if(!address.equals(user.address)) return false;
        if(!phone_number.equals(user.phone_number)) return false;
        return role.equals(user.role);
    }

    @Override
    public String toString(){
        return "User { " + "username" + username + '\'' + ", password" + password + '\'' + ", role" + role + ", address" + address + '\'' + ", name" + name + '\'' + "surname" + surname + '\'' + ", phone number" + phone_number + '\'' + '}';
    }
}
