package com.lucatic.grupo2.app.pay.models.dto;

import com.lucatic.grupo2.app.pay.models.Error;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * StringResponseWithError un mensaje de String encapsulado con el error
 *
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 18-03-2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StringResponseWithError {
	/**
	 * Atributo con objeto Error
	 */
	private Error error;
	/**
	 * Atributo con atributo boolean
	 */
	private String userExistText;
	/**
	 * Atributo con boolean para decidir si es o no un error
	 */
	private boolean isErrorBool;
}
