package br.com.iftech.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenPasswordResponse {

	private String email;
	
	private Long createAtTimestamp;
}
