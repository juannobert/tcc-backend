package br.com.iftech.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.iftech.dtos.requests.RecordRequest;
import br.com.iftech.dtos.responses.RecordResponse;
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
	
	public RecordResponse buscarRegistroDiario(Long id) {
		
		return mapper.toResponse(repository.buscarRegistroRecente(id));
	}
	
	
	private Employee findEmployee(Long userId) {
		return employeeRepository.findById(userId).
				orElseThrow(() -> new UsernameNotFoundException("Usuário inválido"));
	}
	
	
	
}
