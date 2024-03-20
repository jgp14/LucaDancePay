package com.lucatic.grupo2.app.pay.exceptions;

/**
 * Excepción de tipo FeignException para la comunicación de microservicios
 */
public class PayFeignException extends PayException {

    /**
     * Constructor con un parámetro
     * @param message el mensaje de error
     */
    public PayFeignException(String message) {
        super(message);
    }

    /**
     * Constructor con dos parámetros
     * @param message el mensaje de error
     * @param cause error padre
     */
    public PayFeignException(String message, Throwable cause) {
        super(message, cause);
    }
}
