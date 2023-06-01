package br.com.iftech.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iftech.models.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Integer>{
	
	boolean existsByCodigo(String codigo);
	Employer findByCodigo(String codigo);
	
	Optional<Employer> findByEmail(String email);
	
	

}
