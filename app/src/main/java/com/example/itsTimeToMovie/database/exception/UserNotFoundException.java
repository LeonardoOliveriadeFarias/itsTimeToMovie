package com.example.itsTimeToMovie.database.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Usuario nao encontrado.");
    }
}
