package br.com.iftech.dtos.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordResponse {
	
	private Integer id;

	private LocalDateTime horaEntrada;

	private LocalDateTime horaSaida;
	
	private Integer employeeId;
}
