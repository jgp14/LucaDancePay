package com.lucatic.grupo2.app.pay.service;

import com.lucatic.grupo2.app.pay.exceptions.PayException;
import com.lucatic.grupo2.app.pay.models.Pay;
import com.lucatic.grupo2.app.pay.models.dto.BankResponse;
import com.lucatic.grupo2.app.pay.models.dto.PayRequest;
import com.lucatic.grupo2.app.pay.models.dto.PayResponse;
import com.lucatic.grupo2.app.pay.models.dto.PayResponseWithError;

public interface PayService {

    PayResponseWithError managePurchases(PayRequest pay) throws PayException;
    PayResponseWithError analizeResponse(BankResponse bankResponse);
}
