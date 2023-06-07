package br.com.iftech.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends User{
	

	private static final long serialVersionUID = 1L;

	@Column(nullable = false,length = 11)
	private String cpf;
	
	@Column(nullable = false)
	private Date dataNascimento;
	
	
	
	@Column(nullable = false,length = 13)
	private String telefone;
	
	
	private Integer cargaHorariaMensal;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Employer empregador;
	
	

}
