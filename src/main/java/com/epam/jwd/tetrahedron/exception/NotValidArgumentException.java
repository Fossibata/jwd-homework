package com.epam.jwd.tetrahedron.exception;

public class NotValidArgumentException extends Exception{
    public NotValidArgumentException() {
    }
    public NotValidArgumentException(String message) {
        super(message);
    }
}