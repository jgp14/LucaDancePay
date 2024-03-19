package com.lucatic.grupo2.app.pay.controller;

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

import com.lucatic.grupo2.app.pay.model.adapter.PayAdapter;

import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/pay")
public class PayController {
	private final static Logger LOGGER = LogManager.getLogger(PayController.class);
	@Autowired
	private PayService payService;
	@Autowired
	private PayAdapter payAdapter;
	
	@Operation(summary = "Dar de alta un pago", description = "Incluye un nuevo pago en la base de datos", tags = {
	"event" })
@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Usuario creado correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseWithError.class)) }),

	@ApiResponse(responseCode = "400", description = "El usuario ya existe", content = @Content),
	@ApiResponse(responseCode = "500", description = "Error genérico en alta usuario", content = @Content)

})
	@PostMapping
	public ResponseEntity<?> buy(@Valid @RequestBody PayRequest payRequest) throws PayExistException {

		try {
			PayResponseWithError pay = payService.save(payRequest);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
					.toUri();
			LOGGER.info("User " + user.getName() + " with id " + user.getId() + " has been created");
			return ResponseEntity.created(location).body(userAdapter.toEventResponseWithError(user));

		} catch (UserExistException e) {
			LOGGER.warn("Error pushing the event" + e.getMessage());
			throw e;
		}

	}
}
