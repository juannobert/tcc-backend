package br.com.iftech.dtos.responses;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

	private HttpStatus status;
	
	private String path;
	
	private String message;
	
	private LocalDateTime timestamp;
}
