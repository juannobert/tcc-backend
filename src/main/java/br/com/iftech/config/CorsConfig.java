package br.com.iftech.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("*") // Libera para consumo somente as rotas da API
				.allowedOriginPatterns("*") // Libera todas as rotas
				.allowedMethods("*"); //Libera todos os métodos
	}

}
