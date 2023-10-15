package br.com.iftech.dtos.requests;

import br.com.iftech.validations.anotations.CpfOrCnpj;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployerRequest extends UserRequest{
	
	@NotNull
	@NotBlank
	@CpfOrCnpj
	private String cpfCnpj;
	
	@NotNull
	@NotBlank
	private String senha;
	
	@NotNull
	@NotBlank
	private String endereco;
	
}
