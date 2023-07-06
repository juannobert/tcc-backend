package br.com.iftech.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface PermissionsConfig {
	
	@PreAuthorize("hasAnyAuthority('br.com.iftech.models.Employee')")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface isEmployee{}
	
	@PreAuthorize("hasAnyAuthority('br.com.iftech.models.Employer')")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface isEmployer{}
	
	
	@PreAuthorize("isAuthenticated()")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface isAuth{}

}

