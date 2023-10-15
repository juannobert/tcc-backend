package br.com.iftech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iftech.dtos.requests.EmployeeRequest;
import br.com.iftech.dtos.requests.EmployeeUpdateRequest;
import br.com.iftech.dtos.responses.EmployeeResponse;
import br.com.iftech.permissions.PermissionsConfig;
import br.com.iftech.services.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping
	public EmployeeResponse saveEmployee(@Valid @RequestBody EmployeeRequest request) {
		return service.save(request);
	}
	
	@PermissionsConfig.isEmployee
	@GetMapping("/{id}")
	public EmployeeResponse getById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PermissionsConfig.isEmployee
	@PutMapping("/{id}")
	public EmployeeResponse updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateRequest request) {
		return service.update(id, request);
	}
	
	@GetMapping("/empresa/{id}")
	public List<EmployeeResponse> filterEMployee(@PathVariable Long id){
		return service.employeeFilter(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
