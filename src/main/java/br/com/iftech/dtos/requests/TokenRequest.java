package br.com.iftech.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
	
	@Email
	@NotEmpty
	@NotNull
	private String email;
	
	
	@NotEmpty
	@NotNull
	private String senha;

}
