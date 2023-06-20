package br.com.iftech.mappers;

import org.springframework.stereotype.Component;

import br.com.iftech.dtos.responses.RecordResponse;
import br.com.iftech.models.Record;

@Component
public class RecordMapper {
	
	public RecordResponse toResponse(Record record) {
		RecordResponse response = new RecordResponse();
		
		response.setHoraEntrada(record.getHoraEntrada());
		response.setHoraSaida(record.getHoraSaida());
		response.setId(record.getId());
		response.setEmployeeId(record.getEmployee().getId());
		
		return response;
		
		
	}

}
