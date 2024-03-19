package com.lucatic.grupo2.app.pay.controller;

import com.lucatic.grupo2.app.pay.exceptions.PayExceptionBank;
import com.lucatic.grupo2.app.pay.models.Error;
import com.lucatic.grupo2.app.pay.models.dto.PayResponseWithError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

/**
 * Clase Handler que realiza el manejo personalizado de errores
 *
 * @author BlueDevTeam
 * @version 1.0.0
 * @since 15-03-2024
 */
@RestControllerAdvice
public class HandlerProductException {

	/**
	 * Error método error de ejecucion generico.
	 *
	 * @param e La excepción de ejecucion.
	 * @return ResponseEntity con la respuesta con su codigo de error.
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<PayResponseWithError> errorGenericoRuntime(RuntimeException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error procesando petición");
		error.setMessage("Error del tipo " + e.getClass().getSimpleName());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		// return ResponseEntity.internalServerError().body(error);
		PayResponseWithError eventResponseWithError = new PayResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}

	/**
	 * Error de excepcion generica
	 * 
	 * @param e La Excepcion
	 * @return ResponseEntity con la respuesta con su codigo de error.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<PayResponseWithError> errorGenericoRuntime(Exception e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error genérico procesando petición");
		error.setMessage("Error del tipo " + e.getClass().getSimpleName());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		// return ResponseEntity.internalServerError().body(error);
		PayResponseWithError eventResponseWithError = new PayResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<PayResponseWithError> errorGenericoRuntime(MethodArgumentNotValidException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error en los datos del cliente, compruebelos");
		error.setMessage("Error del tipo " + e.getClass().getSimpleName() + " " + e.getMessage().split(":")[1]);
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		// return ResponseEntity.internalServerError().body(error);
		PayResponseWithError eventResponseWithError = new PayResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}


	/**
	 * Error de excepcion no se encontro el Handler.
	 * 
	 * @param e La excepcion de handler no encontrado.
	 * @return esponseEntity con la respuesta del error.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<PayResponseWithError> errorNoHandlerFound(NoHandlerFoundException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error en la URL");
		error.setMessage("Error del tipo " + e.getClass().getSimpleName());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		PayResponseWithError eventResponseWithError = new PayResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		// return ResponseEntity.internalServerError().body(error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}

	/**
	 * Error de excepcion por Resquest Metodo no soportado.
	 * 
	 * @param e La excepcion de metodo no esta soportado.
	 * @return ResponseEntity con la respuesta del error.
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<PayResponseWithError> errorNoHandlerFound(HttpRequestMethodNotSupportedException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error en la URL");
		error.setMessage("Error del tipo " + e.getClass().getSimpleName());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		PayResponseWithError eventResponseWithError = new PayResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		// return ResponseEntity.internalServerError().body(error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}

	@ExceptionHandler(PayExceptionBank.class)
	public ResponseEntity<PayResponseWithError> errorBank(PayExceptionBank e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error en el pago del banco");
		error.setMessage(e.getMessage());
		error.setStatus(Integer.parseInt(e.getMessage().split("\\.")[0]));
		PayResponseWithError eventResponseWithError = new PayResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		// return ResponseEntity.internalServerError().body(error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}


}
