package com.example.yuber.exceptions;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        super("Invalid username and/or password!");
    }
}
