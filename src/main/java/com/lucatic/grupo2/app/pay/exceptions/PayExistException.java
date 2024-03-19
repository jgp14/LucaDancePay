package com.lucatic.grupo2.app.pay.exceptions;

public class PayExistException extends PayException{

    public PayExistException(String message) {
        super(message);
    }

    public PayExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
