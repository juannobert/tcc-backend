package br.com.iftech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iftech.models.Employee;
import br.com.iftech.models.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Integer>{
	
	boolean existsByCodigo(String codigo);
	Employer findByCodigo(String codigo);
	
	

}
