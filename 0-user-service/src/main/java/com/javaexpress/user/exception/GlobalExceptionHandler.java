package com.javaexpress.user.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorApi> handleException(Exception ex) {

		log.error("Something went wrong {}", ex.getMessage());

		ErrorApi errorApi = new ErrorApi();

		errorApi.setMessage(ex.getMessage());
		errorApi.setError(" client Validation error");
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

		return new ResponseEntity<>(errorApi, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorApi> handleException(UserNotFoundException ex) {

		log.error("user Not found Exception {}", ex.getMessage());

		ErrorApi errorApi = new ErrorApi();

		errorApi.setMessage(ex.getMessage());
		errorApi.setError("Validation error");
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());

		return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorApi> handleException(MethodArgumentNotValidException ex) {

		String error = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getField() + ":" + err.getDefaultMessage()).collect(Collectors.joining(","));
		log.error("Client validation exception {}", error);

		ErrorApi errorApi = new ErrorApi();

		errorApi.setMessage(error);
		errorApi.setError("Client Validation error");
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());

		return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorApi> handleException(HttpMessageNotReadableException ex) {

		log.error("HttpMessageNotReadableException {}", ex.getMessage());

		ErrorApi errorApi = new ErrorApi();

		errorApi.setMessage(ex.getMessage());
		errorApi.setError("Malformed JSON data");
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());

		return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
	}

}
