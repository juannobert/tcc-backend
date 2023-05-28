package br.com.iftech.validations.anotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfOrCnpjValidator implements ConstraintValidator<CpfOrCnpj, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true; // Valor nulo é considerado válido, caso seja necessário
		}

		value = value.replaceAll("[^0-9]", ""); // Remove caracteres não numéricos

		// Validação de CPF
		if (value.length() == 11) {
			return validarCPF(value);
		}

		// Validação de CNPJ
		if (value.length() == 14) {
			return validarCNPJ(value);
		}

		return false; 
	}

	private boolean validarCPF(String cpf) {
		
		return cpf.matches("^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$");
	}
	
	private boolean validarCNPJ(String cnpj) {
		
		return cnpj.equals("^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$");
	}
}
