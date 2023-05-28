package br.com.iftech.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = EmpregadorExistsByCodigoValidator.class)
public @interface EmpregadorExistsByCodigo {

		String message() default "Empregador com código ${validatedValue} não existe";

		Class<? extends Payload>[] payload() default {};
		
		Class<?>[] groups () default {};

}
