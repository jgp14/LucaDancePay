package com.lucatic.grupo2.app.pay.models.dto;

import com.lucatic.grupo2.app.pay.models.Error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase modelo DTO PayResponseWithError para las respuestas Posee los getters,
 * setters, toString y constuctores generados por Lombok @Data.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 19-03-2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayResponseWithError {

	/**
	 * Atrubuto Clase PayResponse
	 */
	private PayResponse payResponse;

	/**
	 * Atrubuto clase Error
	 */
	private Error error;

	/**
	 * Atributo boolean si hay error
	 */
	private boolean isErrorBool;
}
