package com.lucatic.grupo2.app.pay.models.adapter;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lucatic.grupo2.app.pay.models.Pay;
import com.lucatic.grupo2.app.pay.models.dto.BankRequest;
import com.lucatic.grupo2.app.pay.models.dto.BankResponse;
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

	/**
	 * Logger que registra los errores de la clase BankAdapter.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(PayAdapter.class);

	public PayResponseWithError toPayResponseWithError(Pay pay) {
		PayResponse payResponse = new PayResponse();
		payResponse.setId(pay.getId());

//		BankResponse bankResponse = new BankResponse(); TODO revisar
//		Long codRespuesta = Long.parseLong(bankResponse.getError());
//		payResponse.setCodRespuesta(codRespuesta);
//		payResponse.setMensaje(bankResponse.getMessage());
		payResponse.setCodRespuesta(0L);
		payResponse.setMensaje(new ArrayList<String>());

		PayResponseWithError payResponseWithError = new PayResponseWithError();
		payResponseWithError.setEventResponse(payResponse);
		payResponseWithError.setErrorBool(false);
		return payResponseWithError;
	}

	/**
	 * Metodo que transforma el dto request del evento en una entidad Pay.
	 * 
	 * @param payRequest se le pasa para ser transformado.
	 * @return Pay La entidad del modelo
	 */
	public Pay fromPayRequest(PayRequest payRequest) {
		Pay pay = new Pay();
		pay.setIdUser(payRequest.getIdUsuario());
		pay.setIdEvent(payRequest.getIdEvento());
		pay.setFinalPrice(payRequest.getPrecioFinal());
		pay.setTickets(payRequest.getNumEntradas());
		pay.setCardNumber(payRequest.getCodigoTarjeta());

		return pay;
	}

	public BankResponse toBankResponse(BankRequest bankRequest) {
		BankResponse bankResponse = new BankResponse();

		bankResponse.setTimestamp("timestamp");
		bankResponse.setStatus("success");
		bankResponse.setError("");
		bankResponse.setMessage(new ArrayList<>());
		bankResponse.setInfo(bankRequest);
		bankResponse.setInfoadicional("Información adicional");

		return bankResponse;
	}

	public BankRequest toBankRequest(BankResponse bankResponse) {
		BankRequest bankRequest = new BankRequest();

		bankRequest.setNombreTitular(bankResponse.getInfo().getNombreTitular());
		bankRequest.setNumeroTarjeta(bankResponse.getInfo().getNumeroTarjeta());
		bankRequest.setMesCaducidad(bankResponse.getInfo().getMesCaducidad());
		bankRequest.setYearCaducidad(bankResponse.getInfo().getYearCaducidad());
		bankRequest.setCvv(bankResponse.getInfo().getCvv());
		bankRequest.setEmisor(bankResponse.getInfo().getEmisor());
		bankRequest.setConcepto(bankResponse.getInfo().getConcepto());
		bankRequest.setCantidad(bankResponse.getInfo().getCantidad());

		return bankRequest;
	}
}
