package com.epam.jwd.tetrahedron.exception;

public class TooManyArgumentException extends Exception{
    public TooManyArgumentException() {
    }
    public TooManyArgumentException(String message) {
        super(message);
    }
}
