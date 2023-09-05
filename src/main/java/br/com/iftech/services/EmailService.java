package br.com.iftech.services;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.iftech.dtos.responses.EmailParams;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Async
	public void sendEmail(EmailParams params) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		
		Context context = new Context();
		
		context.setVariables(params.getProps());
		
		String html = templateEngine.process(params.getTemplate(), context);
		
		try {
			mimeMessageHelper.setFrom("juan2006.dev@gmail.com","Ponto eletrônico");
			mimeMessageHelper.setTo(params.getTo());
			mimeMessageHelper.setSubject(params.getSubject());
			mimeMessageHelper.setText(html,true);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
		
		mailSender.send(mimeMessage);
		
	}
}
