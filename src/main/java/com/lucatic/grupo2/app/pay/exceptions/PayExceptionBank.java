package com.lucatic.grupo2.app.pay.exceptions;

/**
 * Clase personalizada para manejo excepciones de si existe usuario, que hereda
 * de UserException
 *
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 18-03-2024
 */

public class PayExceptionBank extends  PayException {
	
	/**
	 * Constructor que recibe un mensaje del fallo
	 * 
	 * @param message el mensaje de error del bamco
	 */

    public PayExceptionBank(String message) {
        super(message);
    }
    
    /**
	 * Constructor que recibe un mensaje y la causa lanzable
	 * 
	 * @param message el mensaje de error del banco
	 * @param cause   Detalles de motivos de la causa throwable
	 */

    public PayExceptionBank(String message, Throwable cause) {
        super(message, cause);
    }
}
