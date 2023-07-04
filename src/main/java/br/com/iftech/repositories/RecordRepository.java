package br.com.iftech.repositories;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.iftech.models.Record;
public interface RecordRepository extends JpaRepository<Record, Integer> {
	//SELECT r FROM Registro r ORDER BY r.data DESC
	//SELECT * FROM Record WHERE hora_saida  IS NULL and employee_id = 2 ORDER BY hora_entrada DESC;
	
	@Query("SELECT r FROM Record r WHERE r.horaSaida IS NULL and r.employee.id = :userId ORDER BY r.horaEntrada DESC")
	Optional<Record> buscarRegistro(Long userId);
	
	@Query("SELECT r FROM Record r WHERE (:mes = 0 OR MONTH(r.horaEntrada) = :mes) AND r.employee.id = :userId ORDER BY r.id DESC")
	Page<Record> listarRegistros(Long userId,Pageable pageable,Integer mes);

	//SELECT * FROM record WHERE hora_entrada = (SELECT MAX(hora_entrada) FROM Record);
	
	@Query(nativeQuery = true,value = "SELECT * FROM record WHERE employee_id = :userId AND hora_entrada = (SELECT MAX(hora_entrada) FROM Record)")
	Record buscarRegistroRecente(Long userId);
}
