package br.com.iftech.validations.anotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;


import br.com.iftech.exceptions.UserExistsException;
import br.com.iftech.models.User;
import br.com.iftech.repositories.EmployeeRepository;
import br.com.iftech.repositories.EmployerRepository;

@Component
public class UserValidation {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployerRepository employerRepository;
	
	public void validar(User user) {
		validarEmail(user);
	}
	
	private void validarEmail(User user) {
		if(employeeRepository.isEmailJaCdastrado(user) || employerRepository.isEmailJaCdastrado(user)) {
			String msg = "O email digitado já existe";
			FieldError fieldError = new FieldError(user.getClass().getName(), "email", user.getEmail(), false,
					null, null, msg);
			throw new UserExistsException(msg, fieldError);
			
		}
	}
	

}
