package com.lucatic.grupo2.app.pay.models.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase modelo DTO PayResponse para las respuestas en relacion con pagos. Posee
 * los getters, setters, toString y constuctores generados por Lombok @Data.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 19-03-2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayResponse implements Serializable {

	/**
	 * Atributo de serialVersionUID para Serializable.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Atributo Long id de los oagos.
	 */
	private Long id;

	/**
	 * Atributo long con codigo de respuesta.
	 */
	private Long codRespuesta;

	/**
	 * Atributo mensaje de lista de string
	 */
	private List<String> mensaje;

}
