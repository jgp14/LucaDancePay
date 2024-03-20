package com.lucatic.grupo2.app.pay.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lucatic.grupo2.app.pay.exceptions.PayException;
import com.lucatic.grupo2.app.pay.exceptions.PayExceptionBank;
import com.lucatic.grupo2.app.pay.models.dto.BankResponse;
import com.lucatic.grupo2.app.pay.models.dto.PayRequest;
import com.lucatic.grupo2.app.pay.models.dto.PayResponseWithError;

/**
 * Interfaz de servicio
 * 
 * @author BlueDevTeam
 * @version 1.0.0
 * @since 19-03-2024
 */
public interface PayService {
	
	 /**
     * Gestiona las compras realizadas por los usuarios.
     * 
     * @param pay La solicitud de pago que contiene los detalles de la compra.
     * @return PayResponseWithError El resultado del proceso de compra, incluyendo cualquier error si lo hubiera.
     * @throws PayException Si ocurre algún error durante el proceso de compra.
     * @throws JsonProcessingException Si hay algún problema al procesar la solicitud en formato JSON.
     */

    PayResponseWithError managePurchases(PayRequest pay) throws PayException, JsonProcessingException;
    
    /**
     * Analiza la respuesta proporcionada por el banco después de una transacción.
     * 
     * @param bankResponse La respuesta proporcionada por el banco.
     * @param payRequest   La solicitud de pago original que desencadenó la transacción.
     * @return PayResponseWithError El resultado del análisis de la respuesta del banco, incluyendo cualquier error si lo hubiera.
     * @throws PayExceptionBank Si la respuesta del banco indica un error en la transacción.
     */
    public PayResponseWithError analizeResponse(BankResponse bankResponse, PayRequest payRequest) throws PayExceptionBank;
}
