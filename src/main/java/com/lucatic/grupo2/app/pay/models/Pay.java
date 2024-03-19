package com.lucatic.grupo2.app.pay.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase modelo Entity de Pay para la bbdd, requests y responses. Posee los
 * getters, setters y constuctores generados por Lombok
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 19-03-2024
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pay")
public class Pay {
	
	/**
	 * Atributo Long id autoincremental de los pagos.
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
	 * Atributo Long id de los usuarios.
	 */
    @Column(name = "id_user")
    private Long idUser;
    
    /**
	 * Atributo Long id de los eventos.
	 */
    @Column(name = "id_event")
    private Long idEvent;
    
    /**
	 * Atributo Double finalPrice con el precio final.
	 */
    @Column(name = "final_price")
    private Double finalPrice;
    
    /**
   	 * Atributo Integer tickets con el total de entradas.
   	 */
    private Integer tickets;
    
    /**
   	 * Atributo String cardNumber con el número de tarjeta de crédito.
   	 */
    @Column(name = "card_number")
    private String cardNumber;
    
    /**
     * Constructor sin el atributo id de los pagos
   * @param idUser      id del usuario que realiza el pago.
   * @param idEvent     id del evento .
   * @param finalPrice  Precio final de la transacción.
   * @param tickets     Cantidad total de entradas.
   * @param cardNumber  Número de tarjeta de crédito.
     */
    public Pay(Long idUser, Long idEvent, Double finalPrice, Integer tickets, String cardNumber) {
        this.idUser = idUser;
        this.idEvent = idEvent;
        this.finalPrice = finalPrice;
        this.tickets = tickets;
        this.cardNumber = cardNumber;
    }
}
