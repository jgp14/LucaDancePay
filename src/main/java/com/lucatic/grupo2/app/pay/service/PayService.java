package com.lucatic.grupo2.app.pay.service;

import com.lucatic.grupo2.app.pay.models.Pay;
import com.lucatic.grupo2.app.pay.models.dto.PayRequest;
import com.lucatic.grupo2.app.pay.models.dto.PayResponse;

public interface PayService {

    PayResponse managePurchases(PayRequest pay);

    PayResponse analizeResponse(BankResponse bankResponse);
}
