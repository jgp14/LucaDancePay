package com.lucatic.grupo2.app.pay.models.dto;

import com.lucatic.grupo2.app.pay.models.Error;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
