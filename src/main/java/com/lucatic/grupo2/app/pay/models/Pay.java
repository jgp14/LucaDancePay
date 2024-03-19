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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pay")
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "id_event")
    private Long idEvent;
    @Column(name = "final_price")
    private Double finalPrice;
    private Integer tickets;
    @Column(name = "card_number")
    private String cardNumber;

    public Pay(Long idUser, Long idEvent, Double finalPrice, Integer tickets, String cardNumber) {
        this.idUser = idUser;
        this.idEvent = idEvent;
        this.finalPrice = finalPrice;
        this.tickets = tickets;
        this.cardNumber = cardNumber;
    }
}
