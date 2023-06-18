package br.com.iftech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iftech.dtos.responses.UserResponse;
import br.com.iftech.mappers.EmployeeMapper;
import br.com.iftech.mappers.EmployerMapper;
import br.com.iftech.models.Employee;
import br.com.iftech.models.Employer;
import br.com.iftech.models.User;
import br.com.iftech.utils.SecurityUtils;

@Service
public class UserService {

	
	@Autowired
	private SecurityUtils securityUtils;

	
	public UserResponse me() {
		User user = securityUtils.getUsuarioLogado();
		if(user instanceof Employer) {
			EmployerMapper mapper = new EmployerMapper();
			return mapper.toResponse((Employer) user);
		}
		EmployeeMapper mapper = new EmployeeMapper();
		return mapper.toResponse((Employee) user);
	}
}
