package br.com.iftech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.iftech.dtos.requests.RecordRequest;
import br.com.iftech.dtos.responses.MonthFilterResponse;
import br.com.iftech.dtos.responses.RecordResponse;
import br.com.iftech.dtos.responses.WorkingDaysFilterResponse;
import br.com.iftech.mappers.RecordMapper;
import br.com.iftech.models.Employee;
import br.com.iftech.models.Record;
import br.com.iftech.repositories.EmployeeRepository;
import br.com.iftech.repositories.RecordRepository;

@Service
public class RecordService {

	@Autowired
	private RecordRepository repository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RecordMapper mapper;
	
	public RecordResponse registrar(RecordRequest request,Long userId) {
		Employee employee = findEmployee(userId);
		Optional<Record> recordOptional = repository.buscarRegistro(userId);
		Record record;
		if(recordOptional.isPresent()) {
			record = recordOptional.get();
			record.setHoraSaida(request.getData());
		}
		
		else {
			record = new Record();
			record.setHoraEntrada(request.getData());
		}
		record.setEmployee(employee);
		record = repository.save(record);
		
		return mapper.toResponse(record);
		
	}
	
	public Page<RecordResponse> listar(Long userId,Pageable pageable,Integer mes) {
		findEmployee(userId);
		return repository.listarRegistros(userId, pageable,mes)
				.map(mapper::toResponse);
	}
	
	public List<MonthFilterResponse> filtroMensal(Long userId){
		findEmployee(userId);
		return repository.listarFiltroMensal(userId);
		
	}
	
	public List<WorkingDaysFilterResponse> filtroDiario(Long userId){
		findEmployee(userId);
		return repository.listarDiasTrabalhadosMensalmente(userId);
		
	}
	
	public RecordResponse buscarRegistroDiario(Long id) {
		var model = repository.buscarRegistroRecente(id);
		if(model.isPresent()) return mapper.toResponse(model.get());
		return new RecordResponse();
	}
	
	
	
	
	
	private Employee findEmployee(Long userId) {
		return employeeRepository.findById(userId).
				orElseThrow(() -> new UsernameNotFoundException("Usuário inválido"));
	}
}
