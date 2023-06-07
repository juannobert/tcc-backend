package br.com.iftech.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return handleBindException(ex, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatusCode status,
			WebRequest request) {
		HashMap<String, List<String>> body = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
			
			String field = fieldError.getField();

			// Se não exitir uma chave com o nome do campo crie uma e adicione a mensagem
			if (!body.containsKey(field)) {
				List<String> fieldErrors = new ArrayList<>();
				fieldErrors.add(fieldError.getDefaultMessage()); // Adicionando mensagem a lista
				
				body.put(field, fieldErrors);// Inserindo no hashmap
			}
			// Se já exitir uma chave com o nome do campo apenas adicionar a mensagem
			else {
				body.get(field).add(fieldError.getDefaultMessage());
			}
		});
		return ResponseEntity.status(status).body(body);
		
	}


}
