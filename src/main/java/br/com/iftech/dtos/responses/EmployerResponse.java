package br.com.iftech.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor()
@EqualsAndHashCode(callSuper = false)
public class EmployerResponse extends UserResponse{
	
	private String cpfCnpj;
	
	private String endereco;
	
	private String codigo;
}
