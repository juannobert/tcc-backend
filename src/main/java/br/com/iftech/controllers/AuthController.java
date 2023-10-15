package br.com.iftech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iftech.dtos.requests.TokenRequest;
import br.com.iftech.dtos.responses.TokenResponse;
import br.com.iftech.services.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/autenticar")
	public  TokenResponse autenticar(@RequestBody @Valid TokenRequest request) {
		return authService.autenticar(request);
	}
	
	@PostMapping("/token")
	public ResponseEntity<Void> token(){
		return ResponseEntity.ok(null);
	}

}
