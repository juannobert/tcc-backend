package br.com.iftech.validations.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = CpfOrCnpjValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfOrCnpj {
    String message() default "Valor inválido. Deve ser um CPF ou CNPJ válido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}