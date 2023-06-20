package br.com.iftech.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.com.iftech.models.Record;
public interface RecordRepository extends JpaRepository<Record, Integer> {
	
	@Query("SELECT r FROM Record r WHERE r.horaEntrada = (SELECT MAX(r2.horaEntrada) FROM Record r2 WHERE r2.employee.id = :userId and r2.horaSaida IS NULL)")
	Optional<Record> buscarRegistro(Integer userId);

}
