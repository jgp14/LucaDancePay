package com.lucatic.grupo2.app.pay.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucatic.grupo2.app.pay.clientfeign.BankFeignClient;
import com.lucatic.grupo2.app.pay.clientfeign.CheckUserEventFeignClient;
import com.lucatic.grupo2.app.pay.exceptions.PayException;
import com.lucatic.grupo2.app.pay.exceptions.PayExceptionBank;
import com.lucatic.grupo2.app.pay.models.Pay;
import com.lucatic.grupo2.app.pay.models.adapter.PayAdapter;
import com.lucatic.grupo2.app.pay.models.dto.BankResponse;
import com.lucatic.grupo2.app.pay.models.dto.PayRequest;
import com.lucatic.grupo2.app.pay.models.dto.PayResponse;
import com.lucatic.grupo2.app.pay.models.dto.PayResponseWithError;
import com.lucatic.grupo2.app.pay.models.dto.StringResponseWithError;
import com.lucatic.grupo2.app.pay.repository.PayRepository;
import feign.FeignException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Clase que implementa el servicio de pagos.
 *
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 19-03-2024
 */
@Service
public class PayServiceImpl implements PayService {

    private static final Logger LOGGER = LogManager.getLogger(PayServiceImpl.class);

    @Autowired
    private PayAdapter payAdapter;

    @Autowired
    private BankFeignClient bankFeignClient;

    @Autowired
    private CheckUserEventFeignClient checkUserEventFeignClient;

    @Autowired
    private PayRepository payRepository;


    /**
     * Gestiona las compras realizadas por los usuarios.
     *
     * @param payRequest La solicitud de pago que contiene los detalles de la compra.
     * @return PayResponseWithError El resultado del proceso de compra, incluyendo cualquier error si lo hubiera.
     * @throws PayException            Si ocurre algún error durante el proceso de compra.
     * @throws JsonProcessingException Si hay algún problema al procesar la solicitud en formato JSON.
     */
    @Override
    public PayResponseWithError managePurchases(PayRequest payRequest) throws PayException, JsonProcessingException {

        StringResponseWithError stringResponseWithError = null;
        BankResponse bankResponse = null;

        try {
            if (!checkUserEventFeignClient.checkUserEvent(payRequest.getIdUsuario(), payRequest.getIdEvento()).isRespBool()) {
                LOGGER.warn("No se ha verificado el usuario y/o evento");
                throw new PayException("No se ha verificado el usuario y/o el evento");
            }
        } catch (FeignException e) {
            LOGGER.warn(e);
            throw new PayException("Error chekeando microservicio eventmanager para verificar usuario y evento");
        }

        try {

            stringResponseWithError = checkUserEventFeignClient.getNameUser(payRequest.getIdUsuario());
            LOGGER.info(stringResponseWithError);
        } catch (FeignException e) {
            LOGGER.warn(e);
            throw new PayException("Error checkeando nombre de usuario microservicio eventmanager");
        }

        if (!stringResponseWithError.isErrorBool()) {
            try {
                bankResponse = bankFeignClient.processBank(payAdapter.toBankRequest(payRequest, stringResponseWithError.getUserExistText()));
                LOGGER.info("bankResponse : " + bankResponse);
            } catch (FeignException e) {
                try {
                    String responseBody = e.contentUTF8(); // Obtener el cuerpo de la respuesta como String

                    ObjectMapper objectMapper = new ObjectMapper();
                    bankResponse = objectMapper.readValue(responseBody, BankResponse.class);
                } catch (JsonParseException ex) {
                    LOGGER.warn(ex);
                    throw ex;
                }
            }
        }

        if (bankResponse != null)
            return analizeResponse(bankResponse, payRequest);
        else {
            LOGGER.warn("bankResponse null");
            throw new PayException("bankResponse null");
        }
    }

    /**
     * Analiza la respuesta proporcionada por el banco después de una transacción.
     *
     * @param bankResponse La respuesta proporcionada por el banco.
     * @param payRequest   La solicitud de pago original que desencadenó la transacción.
     * @return PayResponseWithError El resultado del análisis de la respuesta del banco, incluyendo cualquier error si lo hubiera.
     * @throws PayExceptionBank Si la respuesta del banco indica un error en la transacción.
     */
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
                        payRequest.getPrecioFinal() * payRequest.getNumEntradas(),
                        payRequest.getNumEntradas(),
                        payRequest.getCodigoTarjeta());
                pay = payRepository.save(pay);
                LOGGER.info(pay + " guardado en bbdd");
                payResponseWithError.setPayResponse(new PayResponse(pay.getId(), Long.parseLong(code), "Pago realizado con éxito", payRequest.getNumEntradas(), payRequest.getPrecioFinal() * payRequest.getNumEntradas(), payRequest.getIdUsuario(), payRequest.getIdEvento()));
                payResponseWithError.setError(null);
                payResponseWithError.setErrorBool(false);
                break;

            case "400":
                LOGGER.info(error);
                throw new PayExceptionBank(error);
                // Error
            case "500":
                LOGGER.info(error);
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
