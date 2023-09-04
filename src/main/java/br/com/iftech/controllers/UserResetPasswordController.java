package br.com.iftech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.iftech.dtos.requests.PasswordResetRequest;
import br.com.iftech.dtos.requests.PasswordUpdateRequest;
import br.com.iftech.models.User;
import br.com.iftech.services.UserPasswordService;
import br.com.iftech.utils.UserUtils;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/public")
public class UserResetPasswordController {
	
	@Autowired
	private UserPasswordService service;
	
	
	@Autowired
	private UserUtils userUtils;
	
	 @PostMapping("/forgot-password")
	    public void forgotPassword(@RequestBody @Valid PasswordResetRequest request) {
	        User user = userUtils.findUserByEmail(request.getEmail());
	        String token = service.generateToken(user);
	        System.out.println(token);
	    }
	 
	 
	 @PostMapping("/change-password")
	 public void changePassowrd(@RequestBody @Valid PasswordUpdateRequest request) {
		   try {
	            service.changePassword(request.getSenha(), request.getToken());
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	        }
	 }
}
