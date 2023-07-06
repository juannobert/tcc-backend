package br.com.iftech.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.iftech.models.Employee;
import br.com.iftech.models.Employer;
import br.com.iftech.models.User;
import br.com.iftech.repositories.EmployeeRepository;
import br.com.iftech.repositories.EmployerRepository;

@Component
public class UserUtils {
	
	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public User findUserByEmail(String username) throws UsernameNotFoundException {
		
		Optional<Employer> employerUser = employerRepository.findByEmail(username);
		Optional<Employee> employeeUser = employeeRepository.findByEmail(username);
		
		if(employerUser.isPresent()) {
			return employerUser.get();
			
		}
		else if(employeeUser.isPresent()) {
			System.out.println(employeeUser.get().getAuthorities().toString());
			return employeeUser.get();
		}
		
		throw new UsernameNotFoundException("Usuário não existe");
		
		
	}
	

}
