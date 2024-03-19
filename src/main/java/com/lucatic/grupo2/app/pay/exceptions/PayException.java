package com.lucatic.grupo2.app.pay.exceptions;

public class PayException extends Exception{

    public PayException(String message) {
        super(message);
    }

    public PayException(String message, Throwable cause) {
        super(message, cause);
    }
}
