package com.lucatic.grupo2.app.pay.exceptions;

/**
 * Clase personalizada de manejo de excepciones relacionados con los pagos
 *
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 19-03-2024
 */

public class PayException extends Exception {

	/** Genera un serial para la excepcion personalizada */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que recibe un mensaje del fallo
	 * 
	 * @param message descriptivo con el error en relacion al pago.
	 */

	public PayException(String message) {
		super(message);
	}

	/**
	 * Constructor que recibe un mensaje y la causa lanzable del fallo
	 * 
	 * @param message descriptivo con el error del pago.
	 * @param cause   Detalles de motivos de la causa throwable.
	 */

	public PayException(String message, Throwable cause) {
		super(message, cause);
	}
}
