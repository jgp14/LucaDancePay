package com.lucatic.grupo2.app.pay.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucatic.grupo2.app.pay.clientfeign.BankFeignClient;
import com.lucatic.grupo2.app.pay.clientfeign.CheckUserEventFeignClient;
import com.lucatic.grupo2.app.pay.exceptions.PayException;
import com.lucatic.grupo2.app.pay.exceptions.PayExceptionBank;
import com.lucatic.grupo2.app.pay.exceptions.PayFeignException;
import com.lucatic.grupo2.app.pay.models.Pay;
import com.lucatic.grupo2.app.pay.models.adapter.PayAdapter;
import com.lucatic.grupo2.app.pay.models.dto.BankResponse;
import com.lucatic.grupo2.app.pay.models.dto.PayRequest;
import com.lucatic.grupo2.app.pay.models.dto.PayResponse;
import com.lucatic.grupo2.app.pay.models.dto.PayResponseWithError;
import com.lucatic.grupo2.app.pay.models.dto.StringResponseWithError;
import com.lucatic.grupo2.app.pay.repository.PayRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayAdapter payAdapter;

    @Autowired
    private BankFeignClient bankFeignClient;

    @Autowired
    private CheckUserEventFeignClient checkUserEventFeignClient;

    @Autowired
    private PayRepository payRepository;

    @Override
    public PayResponseWithError managePurchases(PayRequest payRequest) throws PayException, JsonProcessingException {

        StringResponseWithError stringResponseWithError = null;
        BankResponse bankResponse = null;

        try {
            if (!checkUserEventFeignClient.checkUserEvent(payRequest.getIdUsuario(), payRequest.getIdEvento()).isRespBool()) {
                throw new PayException("No se ha verificado el usuario y/o el evento");
            }

            stringResponseWithError = checkUserEventFeignClient.getNameUser(payRequest.getIdUsuario());
            //System.out.println("------------" + stringResponseWithError );


            bankResponse = bankFeignClient.processBank(payAdapter.toBankRequest(payRequest, stringResponseWithError.getUserExistText()));

        } catch (FeignException e) {

            try {
                String responseBody = e.contentUTF8(); // Obtener el cuerpo de la respuesta como String

                ObjectMapper objectMapper = new ObjectMapper();
                bankResponse = objectMapper.readValue(responseBody, BankResponse.class);
            } catch (JsonParseException ex) {
                throw new JsonParseException("Error no se puede parsear respuesta json microservicios " + ex.getMessage());
            }
        }
        return analizeResponse(bankResponse, payRequest);
    }

    public PayResponseWithError analizeResponse(BankResponse bankResponse, PayRequest payRequest) throws PayExceptionBank {

        String code = bankResponse.getStatus();
        String error = bankResponse.getError();

        PayResponseWithError payResponseWithError = new PayResponseWithError();


        switch (code) {
            case "200":
                // Se ha realizado el pago
                Pay pay = new Pay(
                        payRequest.getIdUsuario(),
                        payRequest.getIdEvento(),
                        payRequest.getPrecioFinal(),
                        payRequest.getNumEntradas(),
                        payRequest.getCodigoTarjeta());
                pay = payRepository.save(pay);
                payResponseWithError.setEventResponse(new PayResponse(pay.getId(), Long.parseLong(code), bankResponse.getMessage()));
                payResponseWithError.setError(null);
                payResponseWithError.setErrorBool(false);
                break;

            case "400":
                throw new PayExceptionBank(error);
                // Error
            case "500":
                throw new PayExceptionBank(error);
                // Error
        }
        return payResponseWithError;

//        200.0001
//        Transacción correcta
//        400.0001
//        No hay fondos suficientes en la cuenta
//        400.0002
//        No se encuentran los datos del cliente
//        400.0003
//        El número de la tarjeta no es válido
//        400.0004
//        El formato del cvv no es válido
//        400.0005
//        El mes (caducidad) no es correcto
//        400.0006
//        El año (caducidad) no es correcto
//        400.0007
//        La fecha de caducidad debe ser posterior al día actual
//        400.0008
//        El formato del nombre no es correcto
//        500.0001
//        El sistema se encuentra inestable

    }
}
