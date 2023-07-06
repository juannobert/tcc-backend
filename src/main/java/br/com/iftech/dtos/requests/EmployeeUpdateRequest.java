package br.com.iftech.dtos.requests;

import java.util.Date;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeUpdateRequest extends UserRequest{
	
	@CPF
	private String cpf;
	
	@NotNull
	private Date dataNascimento;
	
	
	@NotNull
	@NotEmpty
	private String telefone;	
	
	
	
}
