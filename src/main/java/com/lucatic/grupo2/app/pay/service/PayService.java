package com.lucatic.grupo2.app.pay.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lucatic.grupo2.app.pay.exceptions.PayException;
import com.lucatic.grupo2.app.pay.exceptions.PayExceptionBank;
import com.lucatic.grupo2.app.pay.models.Pay;
import com.lucatic.grupo2.app.pay.models.dto.BankResponse;
import com.lucatic.grupo2.app.pay.models.dto.PayRequest;
import com.lucatic.grupo2.app.pay.models.dto.PayResponse;
import com.lucatic.grupo2.app.pay.models.dto.PayResponseWithError;

public interface PayService {

    PayResponseWithError managePurchases(PayRequest pay) throws PayException, JsonProcessingException;
    public PayResponseWithError analizeResponse(BankResponse bankResponse, PayRequest payRequest) throws PayExceptionBank;
}
