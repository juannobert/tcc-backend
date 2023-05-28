package br.com.iftech.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Employer extends User{
	
	@Column(nullable = false,length = 14)
	private String cpfCnpj;
	
	@Column(nullable = false,length = 255)
	private String endereco;
	
	@Column(nullable = false,length = 6)
	private String codigo;
	
	
}
