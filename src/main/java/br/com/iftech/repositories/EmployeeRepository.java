package br.com.iftech.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iftech.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Optional<Employee> findByEmail(String email);
}
