package br.com.iftech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.iftech.dtos.requests.TokenRequest;
import br.com.iftech.dtos.responses.TokenResponse;

@Service
public class AuthService {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	
	@Value("${br.com.iftech.ponto-go.access.expiration}")
	private int expiresIn;
	
	
	public TokenResponse autenticar(TokenRequest request) {
		String email = request.getEmail();
		String senha = request.getSenha();
		
		//Criando objeto de autenticação de usuário
		var autenticacao = new UsernamePasswordAuthenticationToken(email, senha);
		//Autenticando usuário
		authenticationManager.authenticate(autenticacao);
		
		
		//Gerando token
		//Email vai ser o subject
		String access = tokenService.gerarAccessToken(email);
		
		
		return new TokenResponse(access,expiresIn);
	}

}
