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

	@NotBlank
	private String nombreTitular;
	@NotBlank
	private String numeroTarjeta;
	@NotNull
	private Integer mesCaducidad;
	@NotNull
	private Integer yearCaducidad;
	@NotNull
	private Integer cvv;
	@NotBlank
	private String emisor;
	@NotBlank
	private String concepto;
	@NotBlank
	private String cantidad;

}
