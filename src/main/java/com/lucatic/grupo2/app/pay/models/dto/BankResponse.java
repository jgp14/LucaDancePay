package com.lucatic.grupo2.app.pay.models.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase modelo DTO BankResponse para las respuestas en relacion con pagos.
 * Posee los getters, setters, toString y constuctores generados por
 * Lombok @Data.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 19-03-2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankResponse {

	/**
	 * Atributo timestamp
	 */
	@NotBlank
	private String timestamp;
	/**
	 * Atributo estado
	 */
	@NotBlank
	private String status;
	/**
	 * Atributo mesnaje de error
	 */
	@NotBlank
	private String error;

	/**
	 * Atributo mensaje lista de string
	 */
	@NotNull
	private List<String> message = new ArrayList<>();
	/**
	 * Atributo BankRequest informacion de peticion
	 */
	@NotNull
	private BankRequest info;

	/**
	 * Atributo informacion adiciona.
	 */
	@NotBlank
	private String infoadicional;

}
