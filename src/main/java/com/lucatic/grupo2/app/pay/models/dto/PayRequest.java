package com.lucatic.grupo2.app.pay.models.dto;

import jakarta.validation.constraints.NotEmpty;
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

	@NotEmpty
	private Long id_usuario;
	@NotEmpty
	private Long id_evento;
	@NotEmpty
	private int precio_final;
	@NotEmpty
	private int num_entradas;
	@NotEmpty
	private String codigo_tarjeta;
	@NotEmpty
	private int mes;
	@NotEmpty
	private int anio;

}
