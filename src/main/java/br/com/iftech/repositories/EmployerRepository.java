package br.com.iftech.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iftech.models.Employer;
import br.com.iftech.models.User;

public interface EmployerRepository extends JpaRepository<Employer, Long>{
	
	boolean existsByCodigo(String codigo);
	Employer findByCodigo(String codigo);
	
	Optional<Employer> findByEmail(String email);
	
	default boolean isEmailJaCdastrado(User user) {
		if (user.getEmail() == null) {
			return false;
		}

		return findByEmail(user.getEmail())
				.map(usuarioEncontrado -> !usuarioEncontrado.getId().equals(user.getId()))
				.orElse(false);
	}
	
	

}
