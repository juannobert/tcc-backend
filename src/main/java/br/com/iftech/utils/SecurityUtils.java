package br.com.iftech.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.iftech.models.Employee;
import br.com.iftech.models.Employer;
import br.com.iftech.models.User;
import br.com.iftech.repositories.EmployeeRepository;
import br.com.iftech.repositories.EmployerRepository;
@Component
public class SecurityUtils {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployerRepository  employerRepository;
	

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public String getEmailLogado() {
		return getAuthentication().getName();
	}
	
	public User getUsuarioLogado() {
		String email = getEmailLogado();

		Optional<Employer> employerUser = employerRepository.findByEmail(email);
		Optional<Employee> employeeUser = employeeRepository.findByEmail(email);
		
		if(employerUser.isPresent()) 
			return employerUser.get();
		else if(employeeUser.isPresent())
			return employeeUser.get();
		throw new UsernameNotFoundException("Usuário não existe");
		
	}

}
