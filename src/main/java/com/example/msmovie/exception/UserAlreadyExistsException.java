package com.example.msmovie.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super("Username " + username + " already exists");
    }
}
