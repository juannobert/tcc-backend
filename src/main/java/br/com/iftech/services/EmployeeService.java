package br.com.iftech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.iftech.dtos.requests.EmployeeRequest;
import br.com.iftech.dtos.responses.EmployeeResponse;
import br.com.iftech.mappers.EmployeeMapper;
import br.com.iftech.models.Employee;
import br.com.iftech.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private EmployeeMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public EmployeeResponse save(EmployeeRequest request) {
		Employee model = mapper.toModel(request);
		model.setSenha(passwordEncoder.encode(request.getSenha()));
		model = repository.save(model);
		
		return mapper.toResponse(model);
	}
}
