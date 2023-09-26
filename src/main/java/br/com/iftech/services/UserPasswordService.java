package br.com.iftech.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.iftech.dtos.responses.EmailParams;
import br.com.iftech.dtos.responses.TokenPasswordResponse;
import br.com.iftech.models.User;
import br.com.iftech.utils.UserUtils;
import lombok.SneakyThrows;

@Service
public class UserPasswordService {

	@Autowired
	private UserUtils userUtils;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;
	
	@Value("${br.com.iftech.ponto-go.web.url}")
	private String urlWeb;

	@SneakyThrows
	public String generateToken(User user) {
		KeyBasedPersistenceTokenService tokenService = getInstanceFor(user);

		Token token = tokenService.allocateToken(user.getEmail());

		Map<String, Object> props = createProps(token.getKey());
		EmailParams emailParams = createEmailParams(user.getEmail(), props);

		emailService.sendEmail(emailParams);

		return token.getKey();
	}

	@SneakyThrows
	public void changePassword(String newPassword, String rawToken) {
		TokenPasswordResponse publicData = readPublicData(rawToken);

		if (isExpired(publicData)) {
			throw new RuntimeException("Token expirado");
		}

		User user = userUtils.findUserByEmail(publicData.getEmail());

		KeyBasedPersistenceTokenService tokenService = this.getInstanceFor(user);
		tokenService.verifyToken(rawToken);

		user.setSenha(this.passwordEncoder.encode(newPassword));
		userUtils.saveUser(user);
	}

	private boolean isExpired(TokenPasswordResponse publicData) {
		Instant createdAt = new Date(publicData.getCreateAtTimestamp()).toInstant();
		Instant now = new Date().toInstant();
		return createdAt.plus(Duration.ofMinutes(5)).isBefore(now);
	}

	private KeyBasedPersistenceTokenService getInstanceFor(User user) throws Exception {
		KeyBasedPersistenceTokenService tokenService = new KeyBasedPersistenceTokenService();

		tokenService.setServerSecret(user.getPassword());
		tokenService.setServerInteger(16);
		tokenService.setSecureRandom(new SecureRandomFactoryBean().getObject());
		return tokenService;
	}

	private TokenPasswordResponse readPublicData(String rawToken) {
		String rawTokenDecoded = new String(Base64.getDecoder().decode(rawToken));
		String[] tokenParts = rawTokenDecoded.split(":");
		Long timestamp = Long.parseLong(tokenParts[0]);
		String email = tokenParts[2];
		return new TokenPasswordResponse(email, timestamp);
	}

	private Map<String, Object> createProps(String code) {
		String link = urlWeb + "/" + code;
		System.out.println(link);
		HashMap<String, Object> props = new HashMap<String, Object>();
		props.put("code", link);
		return props;
	}

	private EmailParams createEmailParams(String email, Map<String, Object> props) {
		EmailParams emailParams = new EmailParams();
		emailParams.setTo(email);
		emailParams.setSubject("Código de alteração de senha");
		emailParams.setTemplate("email/forget-password");
		emailParams.setProps(props);
		return emailParams;

	}
}
