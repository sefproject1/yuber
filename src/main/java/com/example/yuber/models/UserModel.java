package com.example.yuber.models;

public class UserModel {
    private String username;
    private String password;
    private String surname;
    private String name;
    private String phone_number;
    private String email;
    private String address;
    private String role;
    public int penalty;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public String getPassword() {
        return password;
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

    public UserModel(String username, String password, String surname, String name, String phone_number, String email, String address, String role, int penalty){
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.role = role;
        this.penalty = penalty;
    }

    public UserModel() {
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel user = (UserModel) o;
        if(!username.equals(user.username)) return false;
        if(!password.equals(user.password)) return false;
        if(!name.equals(user.name)) return false;
        if(!surname.equals(user.surname)) return false;
        if(!address.equals(user.address)) return false;
        if(!email.equals(user.email)) return false;
        if(!role.equals(user.role)) return false;
        if (!phone_number.equals(user.phone_number)) return false;
        return penalty == user.penalty;
    }


    /*public int hashcode() {
        int result = username.hashCode();
        result = 31*result + password.hashCode();
        result = 31*result + role.hashCode();
        return result;
    }*/

    @Override
    public String toString(){
        return "User { " + "username:" + username + '\'' + ", password:" + password + '\'' + ", surname:" + surname + '\'' + ", name:" + name + '\'' + ", phone number:" + phone_number + '\'' + ", email:" + email + '\'' + ", address:" + address + '\'' + ", role:" + role + '\'' + '}';
    }
}
