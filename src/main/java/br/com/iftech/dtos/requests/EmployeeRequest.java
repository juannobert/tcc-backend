package br.com.iftech.dtos.requests;

import java.util.Date;

import org.hibernate.validator.constraints.br.CPF;

import br.com.iftech.constraints.EmpregadorExistsByCodigo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeRequest extends UserRequest{
	
	@CPF
	private String cpf;
	
	@NotNull
	private Date dataNascimento;
	
	@NotNull
	@NotBlank
	private String senha;
	
	@NotNull
	@NotEmpty
	private String telefone;
	
	private Integer cargaHorariaMensal;
	
	
	@NotNull
	@NotEmpty
	@Size(max = 6)
	@EmpregadorExistsByCodigo
	private String codigoEmpregador;
	
}
