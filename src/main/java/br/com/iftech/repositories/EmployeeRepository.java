package br.com.iftech.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.iftech.models.Employee;
import br.com.iftech.models.User;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByEmail(String email);
	
	default boolean isEmailJaCdastrado(User user) {
		if (user.getEmail() == null) {
			return false;
		}

		return findByEmail(user.getEmail())
				.map(usuarioEncontrado -> !usuarioEncontrado.getId().equals(user.getId()))
				.orElse(false);
	}
	
	@Query(	value="SELECT * FROM employee WHERE empregador_id = :employerId",nativeQuery = true)
	List<Employee> filterEmployee(Long employerId);
}
