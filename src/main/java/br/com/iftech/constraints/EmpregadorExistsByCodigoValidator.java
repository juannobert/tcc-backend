package br.com.iftech.constraints;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.iftech.repositories.EmployerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmpregadorExistsByCodigoValidator implements ConstraintValidator<EmpregadorExistsByCodigo, String>{

	@Autowired
	private EmployerRepository employerRepository;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(!employerRepository.existsByCodigo(value))
			return false;
		
		return true;
	}
	
	

}
