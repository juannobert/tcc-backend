package br.com.iftech.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
	
	private String accessToken;
	
	private int expiresIn;
	
	private Long userId;
}
