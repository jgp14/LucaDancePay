package com.lucatic.grupo2.app.pay.models.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lucatic.grupo2.app.pay.models.Pay;
import com.lucatic.grupo2.app.pay.models.dto.PayRequest;
import com.lucatic.grupo2.app.pay.models.dto.PayResponse;
import com.lucatic.grupo2.app.pay.models.dto.PayResponseWithError;

/**
 * Clase adapter que convierte entidades a dtos. La entidad Pay a
 * PayResponseWithError y PayRequest a Pay.
 *
 * @author BlueDevTeam
 * @version 1.0.0
 * @since 19-03-2024
 */
@Component
public class PayAdapter {

	private static Logger LOGGER = LoggerFactory.getLogger(PayAdapter.class);

	public PayResponseWithError toPayResponseWithError(Pay pay) {
		PayResponse payResponse = new PayResponse();
		payResponse.setId(pay.getId());
		
		
		payResponse.setCodRespuesta(1L);
		payResponse.setMensaje("");
		
		PayResponseWithError payResponseWithError = new PayResponseWithError();
		payResponseWithError.setEventResponse(payResponse);
		payResponseWithError.setErrorBool(false);
		return payResponseWithError;
	}
	
	public Pay fromPayRequest(PayRequest payRequest) {
		Pay pay  = new Pay();
		pay.setIdUser(payRequest.getIdUsuario());
		
		return null;
	}
}
