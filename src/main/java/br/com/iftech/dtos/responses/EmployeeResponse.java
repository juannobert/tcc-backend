package br.com.iftech.dtos.responses;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeResponse extends UserResponse{
	private String cpf;
	
	private Date dataNascimento;
	
	private String telefone;
	
	private Integer cargaHorariaMensal;
	
	private Integer empregadorId;
}
