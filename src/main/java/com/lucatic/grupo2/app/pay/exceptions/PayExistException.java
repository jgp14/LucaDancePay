package com.lucatic.grupo2.app.pay.exceptions;

/**
 * Clase personalizada para manejo excepciones de si existe pago, que hereda
 * de PayException
 *
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 19-03-2024
 */

public class PayExistException extends PayException{
	
	/**
	 * Constructor que recibe un mensaje del fallo
	 * 
	 * @param message el mensaje de error del pago ya exitente
	 */

    public PayExistException(String message) {
        super(message);
    }
    
    /**
	 * Constructor que recibe un mensaje y la causa lanzable
	 * 
	 * @param message el mensaje de error del pago ya exitente
	 * @param cause   Detalles de motivos de la causa throwable
	 */

    public PayExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
