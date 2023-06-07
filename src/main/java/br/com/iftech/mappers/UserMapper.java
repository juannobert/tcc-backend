package br.com.iftech.mappers;

import br.com.iftech.dtos.requests.UserRequest;
import br.com.iftech.dtos.responses.UserResponse;
import br.com.iftech.models.User;

public class UserMapper {
	
	public void toModelBase(UserRequest request,User model) {
		model.setEmail(request.getEmail());
		model.setNome(request.getSenha());
	}
	
	public void toResponseBase(User model,UserResponse response) {
		response.setId(model.getId());	
		response.setEmail(model.getEmail());
		response.setNome(model.getNome());
	
		
		
	}
	
	

}
