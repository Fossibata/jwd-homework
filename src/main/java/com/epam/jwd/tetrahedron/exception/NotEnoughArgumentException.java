package com.epam.jwd.tetrahedron.exception;

public class NotEnoughArgumentException extends  Exception{
    public NotEnoughArgumentException() {
    }
    public NotEnoughArgumentException(String message) {
        super(message);
    }
}
