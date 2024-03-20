package com.lucatic.grupo2.app.pay.exceptions;

public class PayFeignException extends PayException {
    public PayFeignException(String message) {
        super(message);
    }

    public PayFeignException(String message, Throwable cause) {
        super(message, cause);
    }
}
