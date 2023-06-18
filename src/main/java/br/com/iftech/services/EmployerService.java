package br.com.iftech.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.iftech.dtos.requests.EmployerRequest;
import br.com.iftech.dtos.responses.EmployerResponse;
import br.com.iftech.mappers.EmployerMapper;
import br.com.iftech.models.Employer;
import br.com.iftech.repositories.EmployerRepository;
import br.com.iftech.validations.anotations.UserValidation;

@Service
public class EmployerService {

	@Autowired
	private EmployerRepository repository;

	@Autowired
	private EmployerMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserValidation userValidation;
	
	public EmployerResponse save(EmployerRequest request) {
		Employer model = mapper.toModel(request);
		model.setSenha(passwordEncoder.encode(request.getSenha()));
		model.setCodigo(gerarCodigo());
		
		userValidation.validar(model);
		model = repository.save(model);
		
		
		
		
		return mapper.toResponse(model);
		
	}
	
	
	private String gerarCodigo() {
		String codigo = "";
		Random r = new Random();
		for(int i = 1; i <= 6;i++) {
			codigo += String.valueOf(r.nextInt(9));
		}
		
		verificarCodigo(codigo);
		return codigo;
		
	}
	
	private void verificarCodigo(String codigo) {
		if(repository.existsByCodigo(codigo))
			gerarCodigo();
		
	}
	
	
}
