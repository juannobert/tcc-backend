package br.com.iftech.dtos.requests;

import br.com.iftech.validations.anotations.CpfOrCnpj;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployerUpdateRequest extends UserRequest{
	
	@NotNull
	@NotBlank
	private String endereco;
	
	@NotNull
	@NotBlank
	@CpfOrCnpj
	private String cpfCnpj;
	
	
	

}
