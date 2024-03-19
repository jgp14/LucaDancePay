package com.lucatic.grupo2.app.pay.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase modelo DTO PayRequest para peticiones. Tiene implementados getters,
 * setters toString y constructores generados por Lombok @Data
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 18-03-2024
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayRequest {

	/**
	 * Atributo id de usuario
	 */
	@NotNull
	private Long idUsuario;

	/**
	 * Atributo id de evento
	 */
	@NotNull
	private Long idEvento;
	/**
	 * Atributo entero de precio final
	 */
	@NotNull
	private Double precioFinal;

	/**
	 * Atributo entero de numero de entradas
	 */
	@NotNull
	private Integer numEntradas;

	/**
	 * Atributo string de codigo de tarjeta
	 */
	@NotBlank
	private String codigoTarjeta;

	/**
	 * Atributo entero del mes caducidad
	 */
	@NotNull
	private Integer mes;

	/**
	 * Atributo anio de la caducidad
	 */
	@NotNull
	private Integer anio;

	private Integer cvv;

	private String emisor;

	private String concept;

}
