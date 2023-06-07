package br.com.iftech.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.iftech.models.Employee;
import br.com.iftech.models.Employer;
import br.com.iftech.repositories.EmployeeRepository;
import br.com.iftech.repositories.EmployerRepository;

@Service
public class UserAuthService implements UserDetailsService{

	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Employer> employerUser = employerRepository.findByEmail(username);
		Optional<Employee> employeeUser = employeeRepository.findByEmail(username);
		
		if(employerUser.isPresent()) {
			return employerUser.get();
			
		}
		else if(employeeUser.isPresent()) {
			return employeeUser.get();
		}
		throw new UsernameNotFoundException("Usuário não existe");
		
		
	}
	
	

}
