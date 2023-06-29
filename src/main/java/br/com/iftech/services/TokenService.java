package br.com.iftech.services;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenService {

	@Value("${br.com.iftech.ponto-go.access.key}")
	private String tokenKey;
	
	@Value("${br.com.iftech.ponto-go.access.expiration}")
	private int tokenExpiration;
	
	
	public String gerarAccessToken(String subject) {
		return gerarToken(subject, tokenKey, tokenExpiration);
	}

	public String getSubjectDoAccessToken(String accessToken) {
		//Buscando informações do usuário que estão em um token
		return getClaims(accessToken,tokenKey)
				.getSubject();
	}
	
	
	private Claims getClaims(String accessToken,String signinKey) {
		try {
		return Jwts.parser()
				.setSigningKey(signinKey)
				.parseClaimsJws(accessToken)
				.getBody();
		}catch(JwtException e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}

	private String gerarToken(String subject,String signinKey,int expiration) {
		//claims: São atributos que definem o uso do JWT e informações úteis para a aplicação.
		Map<String,Object> claims = new HashMap<>();
		
		Instant dataHoraAtual = Instant.now();
		Instant dataHoraExpiracao = dataHoraAtual.plusSeconds(tokenExpiration);
		
		//Gerando Token JWT
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(dataHoraAtual.toEpochMilli()))
				.setExpiration(new Date(dataHoraExpiracao.toEpochMilli()))
				//Algoritmo de criptografia e assinatura/chave ddo token
				.signWith(SignatureAlgorithm.HS512, signinKey)
				.compact();
	}

}
