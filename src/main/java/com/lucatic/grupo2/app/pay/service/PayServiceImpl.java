package com.lucatic.grupo2.app.pay.service;

import com.lucatic.grupo2.app.pay.clientfeign.BankFeignClient;
import com.lucatic.grupo2.app.pay.clientfeign.CheckUserEventFeignClient;
import com.lucatic.grupo2.app.pay.exceptions.PayException;
import com.lucatic.grupo2.app.pay.models.adapter.PayAdapter;
import com.lucatic.grupo2.app.pay.models.dto.PayRequest;
import com.lucatic.grupo2.app.pay.models.dto.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayAdapter payAdapter;

    @Autowired
    private BankFeignClient bankFeignClient;

    @Autowired
    private CheckUserEventFeignClient checkUserEventFeignClient;

    @Override
    public PayResponse managePurchases(PayRequest payRequest) {

        if (!checkUserEventFeignClient.checkUserEvent(payRequest.getIdUsuario(), payRequest.getIdEvento())) {
            throw new PayException("No se ha verificado el usuario y/o el evento");
        }

        BankResponse bankResponse = bankFeignClient.processBank(payAdapter.toBankRequest(payRequest));
        return analizeResponse(bankResponse);

    }

    public PayResponse analizeResponse(BankResponse bankResponse) {

    }
}
