package br.com.iftech.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	@Email(message = "Digite um email válido!")
	@NotBlank(message = "O email não pode estar em branco!")
	@NotNull(message = "O email não pode ser nulo!")
	private String email;
	
	
	
	@NotNull
	@NotBlank
	private String nome;
}
