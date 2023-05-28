package br.com.iftech.mappers;

import org.springframework.stereotype.Component;

import br.com.iftech.dtos.requests.EmployerRequest;
import br.com.iftech.dtos.responses.EmployerResponse;
import br.com.iftech.models.Employer;

@Component
public class EmployerMapper {

	public Employer toModel(EmployerRequest request) {
		Employer model = new Employer();
		model.setEmail(request.getEmail());
		model.setEndereco(request.getEndereco());
		model.setCpfCnpj(request.getCpfCnpj());
		model.setNome(request.getNome());
		return model;
	}
	
	public EmployerResponse toResponse(Employer model) {
		EmployerResponse response = new EmployerResponse();
		response.setId(model.getId());
		response.setCodigo(model.getCodigo());
		response.setCpfCnpj(model.getCpfCnpj());
		response.setEmail(model.getEmail());
		response.setNome(model.getNome());
		response.setEndereco(model.getEndereco());
		return response;
	}
}
