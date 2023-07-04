package br.com.iftech.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.iftech.dtos.requests.EmployeeRequest;
import br.com.iftech.dtos.requests.EmployeeUpdateRequest;
import br.com.iftech.dtos.responses.EmployeeResponse;
import br.com.iftech.models.Employee;
import br.com.iftech.models.Employer;
import br.com.iftech.repositories.EmployerRepository;

@Component
public class EmployeeMapper extends UserMapper{
	
	@Autowired
	private EmployerRepository employerRepository;
	
	public Employee toModel(EmployeeRequest request) {
		Employee model = new Employee();
		super.toModelBase(request, model);
		model.setCpf(request.getCpf());
		model.setTelefone(request.getTelefone());
		model.setCargaHorariaMensal(request.getCargaHorariaMensal());
		model.setDataNascimento(request.getDataNascimento());
		model.setEmpregador(getEmployerByCodigo(request.getCodigoEmpregador()));
		return model;
	}
	
	public EmployeeResponse toResponse(Employee model) {
		EmployeeResponse response = new EmployeeResponse();
		super.toResponseBase(model, response);
		response.setTelefone(model.getTelefone());
		response.setCpf(model.getCpf());
		response.setDataNascimento(model.getDataNascimento());
		response.setCargaHorariaMensal(model.getCargaHorariaMensal());
		response.setEmpregadorId(model.getEmpregador().getId());
		return response;
		
	}
	
	public Employee updateToModel(Employee model,EmployeeUpdateRequest request) {
		model.setCpf(request.getCpf());
		model.setEmail(request.getEmail());
		model.setNome(request.getNome());
		model.setTelefone(request.getTelefone());
		model.setDataNascimento(request.getDataNascimento());
		
		return model;
	}
	
	private Employer getEmployerByCodigo(String codigo) {
		return employerRepository.findByCodigo(codigo);
	}
	

}
