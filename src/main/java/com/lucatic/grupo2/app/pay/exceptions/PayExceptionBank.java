package com.lucatic.grupo2.app.pay.exceptions;

public class PayExceptionBank extends  PayException {

    public PayExceptionBank(String message) {
        super(message);
    }

    public PayExceptionBank(String message, Throwable cause) {
        super(message, cause);
    }
}
