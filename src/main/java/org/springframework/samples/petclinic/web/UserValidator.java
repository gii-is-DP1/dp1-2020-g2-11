package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Cliente cliente=(Cliente) target;
		String username = cliente.getUser().getUsername();
		String password = cliente.getUser().getPassword();
		if((username.startsWith(" ") || username.isEmpty())) {
			errors.rejectValue("marca", "no puede empezar por espacio ni estar vacio", "no puede empezar por espacio ni estar vacio");

		}
		if((password.startsWith(" ") || password.isEmpty())) {
			errors.rejectValue("marca", "no puede empezar por espacio ni estar vacio", "no puede empezar por espacio ni estar vacio");

		}
	}

}
