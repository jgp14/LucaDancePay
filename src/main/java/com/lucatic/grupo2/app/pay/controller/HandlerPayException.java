package com.lucatic.grupo2.app.pay.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.lucatic.grupo2.app.pay.exceptions.PayException;
import com.lucatic.grupo2.app.pay.exceptions.PayExceptionBank;
import com.lucatic.grupo2.app.pay.exceptions.PayFeignException;
import com.lucatic.grupo2.app.pay.models.Error;
import com.lucatic.grupo2.app.pay.models.dto.PayResponseWithError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
public class HandlerPayException {

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
		error.setMessage("Error del tipo genérico");
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
		error.setMessage("Error del tipo genérico");
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		// return ResponseEntity.internalServerError().body(error);
		PayResponseWithError eventResponseWithError = new PayResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}

	/**
	 * Error de tipo argumento de método no válido
	 * 
	 * @param e La excepción
	 * @return Un BADREQUEST con eventResponseWithError
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<PayResponseWithError> errorGenericoRuntime(MethodArgumentNotValidException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error en los datos del cliente, compruebelos");
		error.setMessage(e.getMessage().split(":")[1]);
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
		error.setMessage("Error buscando manejador de petición");
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
		error.setMessage("Error en el tipo de método HTTP de peticion");
		error.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		PayResponseWithError eventResponseWithError = new PayResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		// return ResponseEntity.internalServerError().body(error);
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body(eventResponseWithError);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<PayResponseWithError> errorHttpMessageNotReadable(HttpMessageNotReadableException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error en los campos del json");
		error.setMessage("Introduce correctamente los datos y su formato");
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		PayResponseWithError eventResponseWithError = new PayResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		// return ResponseEntity.internalServerError().body(error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}

	/**
	 * Error con transacciones con el banco
	 * 
	 * @param e La excepcion
	 * @return Mensaje de error
	 */
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

	/**
	 * Error genérico de Pay
	 * 
	 * @param e el error
	 * @return INTERNAL_SERVER_ERROR con el eventResponseWithError
	 */
	@ExceptionHandler(PayException.class)
	public ResponseEntity<PayResponseWithError> errorPayException(PayException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error en la gestión de la aplicación");
		error.setMessage(e.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		PayResponseWithError eventResponseWithError = new PayResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		// return ResponseEntity.internalServerError().body(error);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(eventResponseWithError);
	}

	/**
	 * Errores de feign
	 * 
	 * @param e La excepción
	 * @return un payResponseWithError con BAD_REQUEST
	 */
	@ExceptionHandler(PayFeignException.class)
	public ResponseEntity<PayResponseWithError> errorFeigngetUsername(PayFeignException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error en la búsqueda de username");
		error.setMessage(e.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		PayResponseWithError payResponseWithError = new PayResponseWithError();
		payResponseWithError.setError(error);
		payResponseWithError.setErrorBool(true);
		payResponseWithError.setPayResponse(null);
		// return ResponseEntity.internalServerError().body(error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(payResponseWithError);
	}

	/**
	 * Error de tipo parseo de json
	 * 
	 * @param e La excepción de json
	 * @return un INTERNAL_SERVER_ERROR con payResponseWithError
	 */
	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<PayResponseWithError> errorFeigngetUsername(JsonParseException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error conviertiendo json de conexión entre sistemas");
		error.setMessage("Error conviertiend json de conexión entre sistemas");
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		PayResponseWithError payResponseWithError = new PayResponseWithError();
		payResponseWithError.setError(error);
		payResponseWithError.setErrorBool(true);
		payResponseWithError.setPayResponse(null);
		// return ResponseEntity.internalServerError().body(error);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(payResponseWithError);
	}

}
