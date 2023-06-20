package br.com.iftech.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iftech.dtos.requests.RecordRequest;
import br.com.iftech.dtos.responses.RecordResponse;
import br.com.iftech.mappers.RecordMapper;
import br.com.iftech.models.Record;
import br.com.iftech.repositories.RecordRepository;
import br.com.iftech.utils.SecurityUtils;

@Service
public class RecordService {

	@Autowired
	private RecordRepository repository;
	
	@Autowired
	private SecurityUtils securityUtils;
	
	@Autowired
	private RecordMapper mapper;
	
	public RecordResponse registrar(RecordRequest request) {
		
		Integer userId = securityUtils.getUsuarioLogado().getId();
		
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
			
		record = repository.save(record);
		
		return mapper.toResponse(record);
		
	}
	
	
	
}
