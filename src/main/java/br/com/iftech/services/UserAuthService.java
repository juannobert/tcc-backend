package br.com.iftech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.iftech.utils.UserUtils;

@Service
public class UserAuthService implements UserDetailsService{

	@Autowired
	private UserUtils userUtils;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userUtils.findUserByEmail(username);
				
	}
	
	

}
