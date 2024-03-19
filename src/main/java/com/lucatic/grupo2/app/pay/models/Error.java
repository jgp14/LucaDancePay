package com.lucatic.grupo2.app.pay.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Clase modelo de error que se muestra en los responses si da fallo
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 19-03-2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
	/**
	 * Atributo mensaje con el mensaje o descripcion del error
	 */
	private String message;
	/**
	 * Atributo con el error
	 */
	private String error;
	/**
	 * Atributo con el estado del error.
	 */
	private int status;
	/**
	 * Atributo fecha y hora que se produjo el error.
	 */
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime date;
}
