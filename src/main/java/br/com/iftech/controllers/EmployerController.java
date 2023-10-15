package br.com.iftech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iftech.dtos.requests.EmployerRequest;
import br.com.iftech.dtos.requests.EmployerUpdateRequest;
import br.com.iftech.dtos.responses.EmployerResponse;
import br.com.iftech.permissions.PermissionsConfig;
import br.com.iftech.services.EmployerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/empregadores")
@CrossOrigin
public class EmployerController {
	
	@Autowired
	private EmployerService service;
	
	@PostMapping
	public EmployerResponse saveEmployer(@Valid @RequestBody EmployerRequest request) {
		return service.save(request);
	}
	
	@PermissionsConfig.isEmployer
	@GetMapping("/{id}")
	public EmployerResponse getById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PermissionsConfig.isEmployer
	@PutMapping("/{id}")
	public EmployerResponse updateEmployer(@PathVariable Long id, @Valid @RequestBody EmployerUpdateRequest request) {
		return service.update(id, request);
	}
}
