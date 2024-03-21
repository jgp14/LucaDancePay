package com.lucatic.grupo2.app.pay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lucatic.grupo2.app.pay.exceptions.PayException;
import com.lucatic.grupo2.app.pay.exceptions.PayExceptionBank;
import com.lucatic.grupo2.app.pay.models.dto.PayRequest;
import com.lucatic.grupo2.app.pay.models.dto.PayResponseWithError;
import com.lucatic.grupo2.app.pay.service.PayService;
import io.swagger.v3.core.util.Json;
import lombok.ToString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LucaDancePayApplicationTests {

	@Autowired
	private PayService payService;

	@Test
	void contextLoads() {
		Assertions.assertThat(true).isTrue();
	}

	@Test
	public void testNumberCardIncorrect() {
		PayRequest payRequest = new PayRequest();
		payRequest.setAnio(2024);
		payRequest.setCvv(444);
		payRequest.setEmisor("BBVA");
		payRequest.setConcept("Baile");
		payRequest.setCodigoTarjeta("44444a44444444444");
		payRequest.setMes(12);
		payRequest.setNumEntradas(2);
		payRequest.setPrecioFinal(80D);
		payRequest.setIdUsuario(2L);
		payRequest.setIdEvento(25L);

		assertThrows(PayExceptionBank.class, () -> payService.managePurchases(payRequest));
	}

	@Test
	public void testIfUserNoExist() {
		PayRequest payRequest = new PayRequest();
		payRequest.setAnio(2024);
		payRequest.setCvv(444);
		payRequest.setEmisor("BBVA");
		payRequest.setConcept("Baile");
		payRequest.setCodigoTarjeta("4444444444444444");
		payRequest.setMes(12);
		payRequest.setNumEntradas(2);
		payRequest.setPrecioFinal(80D);
		payRequest.setIdUsuario(20L);
		payRequest.setIdEvento(25L);

		assertThrows(PayException.class, () -> payService.managePurchases(payRequest));
	}

	@Test
	public void testIfYearIncorrect() {
		PayRequest payRequest = new PayRequest();
		payRequest.setAnio(2024);
		payRequest.setCvv(444);
		payRequest.setEmisor("BBVA");
		payRequest.setConcept("Baile");
		payRequest.setCodigoTarjeta("4444444444444444");
		payRequest.setMes(12);
		payRequest.setNumEntradas(2);
		payRequest.setPrecioFinal(80D);
		payRequest.setIdUsuario(20L);
		payRequest.setIdEvento(25L);

		assertThrows(PayException.class, () -> payService.managePurchases(payRequest));
	}

}
