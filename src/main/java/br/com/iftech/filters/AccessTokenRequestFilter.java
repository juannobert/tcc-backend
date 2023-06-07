package br.com.iftech.filters;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.iftech.dtos.responses.ErrorResponse;
import br.com.iftech.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AccessTokenRequestFilter extends OncePerRequestFilter {

	private final static String TOKEN_TYPE = "Bearer ";

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private ObjectMapper objectMapper;

	// Validador de um token JWT
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		doFilter(request, response, filterChain);
	}

	private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			String token = "";
			String email = "";

			// Pegando token no cabeçalho da requisição
			String authorizationHeader = request.getHeader("Authorization");
			if (isTokenPresente(authorizationHeader)) {
				// Desconsidera a palavra Bearer quando for buscar o token
				token = authorizationHeader.substring(TOKEN_TYPE.length());
				email = tokenService.getSubjectDoAccessToken(token);
			}

			if (isEmailNotInContext(email)) {
				addEmailInContext(request, email);
			}

			filterChain.doFilter(request, response);
		} catch (RuntimeException e) {
			HttpStatus status = HttpStatus.FORBIDDEN;
			System.out.println(e.getLocalizedMessage());
			var errorResponse = ErrorResponse.builder()
					.status(status)
					.message(e.getLocalizedMessage())
					.path(request.getRequestURI())
					.timestamp(LocalDateTime.now())
					.build();
			
			String json = objectMapper.writeValueAsString(errorResponse);
			
			response.setStatus(status.value());
			response.setHeader("Content-Type", "application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(json);
		}
	}

	private boolean isTokenPresente(String authorizationHeader) {
		// Verificando se não é nulo e se é um token bearer
		return authorizationHeader != null && authorizationHeader.startsWith(TOKEN_TYPE);
	}

	private Boolean isEmailNotInContext(String email) {
		// Verificando se o email não está no contexto do spring security
		return !email.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null;
	}

	private void addEmailInContext(HttpServletRequest request, String email) {
		// Adicionando email no contexto do spring security
		var usuario = userDetailsService.loadUserByUsername(email);

		var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		autenticacao.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(autenticacao);

	}

}