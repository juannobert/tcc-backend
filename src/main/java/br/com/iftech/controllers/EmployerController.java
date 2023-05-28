package br.com.iftech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iftech.dtos.requests.EmployerRequest;
import br.com.iftech.dtos.responses.EmployerResponse;
import br.com.iftech.services.EmployerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/empregadores")
public class EmployerController {
	
	@Autowired
	private EmployerService service;
	
	@PostMapping
	public EmployerResponse saveEmployer(@Valid @RequestBody EmployerRequest request) {
		return service.save(request);
	}
}
