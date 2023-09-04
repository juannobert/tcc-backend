package br.com.iftech.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordUpdateRequest {

	@NotEmpty
	private String senha;
	
	@NotEmpty
	private String token;
}
