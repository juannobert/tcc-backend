package br.com.iftech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iftech.dtos.requests.EmployeeRequest;
import br.com.iftech.dtos.responses.EmployeeResponse;
import br.com.iftech.services.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping
	public EmployeeResponse saveEmployee(@Valid @RequestBody EmployeeRequest request) {
		return service.save(request);
	}
	
	@GetMapping("/{id}")
	public EmployeeResponse getById(@PathVariable Long id) {
		return service.findById(id);
		
	}
}
