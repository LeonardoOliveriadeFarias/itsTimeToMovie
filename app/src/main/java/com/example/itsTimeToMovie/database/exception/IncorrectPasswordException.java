package com.example.itsTimeToMovie.database.exception;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {
        super("Senha invalida.");
    }
}
