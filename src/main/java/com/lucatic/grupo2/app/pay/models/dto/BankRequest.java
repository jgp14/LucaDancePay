package com.lucatic.grupo2.app.pay.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase modelo DTO BankRequest para peticiones al microservicio del banco.
 * Tiene implementados getters, setters toString y constructores generados por
 * Lombok @Data
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 18-03-2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankRequest {
	/**
	 * Atributo String con el nombre del titular.
	 */
	@NotBlank
	private String nombreTitular;
	/**
	 * Atributo String con el número de la tarjeta.
	 */
	@NotBlank
	private String numeroTarjeta;
	/**
	 * Atributo entero del mes caducidad.
	 */
	@NotNull
	private Integer mesCaducidad;
	/**
	 * Atributo entero anio de la caducidad.
	 */
	@NotNull
	private Integer yearCaducidad;
	@NotNull
	/**
	 * Atributo entero cvv de la tarjeta.
	 */
	private Integer cvv;
	@NotBlank
	/**
	 * Atributo String nombre del emisor.
	 */
	private String emisor;
	/**
	 * atributo String del concepto.
	 */
	@NotBlank
	private String concepto;
	/**
	 * Atributo entero con la cantidad total.
	 */
	@NotNull
	private Double cantidad;

}
