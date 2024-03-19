package com.lucatic.grupo2.app.pay.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lucatic.grupo2.app.pay.exceptions.PayException;
import com.lucatic.grupo2.app.pay.exceptions.PayExceptionBank;
import com.lucatic.grupo2.app.pay.exceptions.PayExistException;
import com.lucatic.grupo2.app.pay.models.adapter.PayAdapter;
import com.lucatic.grupo2.app.pay.models.dto.PayRequest;
import com.lucatic.grupo2.app.pay.models.dto.PayResponseWithError;
import com.lucatic.grupo2.app.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/pay")
public class PayController {
	private final static Logger LOGGER = LogManager.getLogger(PayController.class);

	@Autowired
	private PayService payService;


	@Operation(summary = "Dar de alta un pago", description = "Incluye un nuevo pago en la base de datos", tags = {
			"event" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pago creado correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PayResponseWithError.class)) }),

			@ApiResponse(responseCode = "400", description = "El papgo ya existe", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error genérico en alta de pago", content = @Content)

	})
	@PostMapping
	public ResponseEntity<?> buy(@Valid @RequestBody PayRequest payRequest) throws PayException, JsonProcessingException {

		try {
			PayResponseWithError payResponseWithError = payService.managePurchases(payRequest);

			LOGGER.info(payResponseWithError);
			return ResponseEntity.ok(payResponseWithError);

		} catch (PayExceptionBank e) {
			LOGGER.warn(e);
			throw e;
		} catch (PayException e) {
			LOGGER.warn("Error pushing the event" + e.getMessage());
			throw e;
		} catch (JsonParseException e) {
            LOGGER.warn(e.getMessage());
			throw e;
        }
    }
}
