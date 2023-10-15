package br.com.iftech.mappers;

import org.springframework.stereotype.Component;

import br.com.iftech.dtos.requests.EmployerRequest;
import br.com.iftech.dtos.requests.EmployerUpdateRequest;
import br.com.iftech.dtos.responses.EmployerResponse;
import br.com.iftech.models.Employer;

@Component
public class EmployerMapper  extends UserMapper{

	public Employer toModel(EmployerRequest request) {
		Employer model = new Employer();
		super.toModelBase(request, model);
		model.setEndereco(request.getEndereco());
		model.setCpfCnpj(request.getCpfCnpj());
		return model;
	}
	
	public EmployerResponse toResponse(Employer model) {
		EmployerResponse response = new EmployerResponse();
		super.toResponseBase(model, response);
		response.setCodigo(model.getCodigo());
		response.setCpfCnpj(model.getCpfCnpj());
		response.setEndereco(model.getEndereco());
		return response;
	}
	
	public Employer updateToModel(Employer model,EmployerUpdateRequest request) {
		model.setCpfCnpj(request.getCpfCnpj());
		model.setEmail(request.getEmail());
		model.setNome(request.getNome());
		model.setEndereco(request.getEndereco());
		return model;
	}
}
