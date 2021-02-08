package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VehiculoValidator implements Validator {
	
	@Override
	public void validate(Object obj, Errors errors) {

		Vehiculo vehiculo= (Vehiculo) obj;
		LocalDate fechaFabricacion= vehiculo.getFechaFabricacion();
		
		if (LocalDate.now().getYear()-fechaFabricacion.getYear()>=12) {
			errors.rejectValue("fechaFabricacion", " No podemos atender un vehiculo con mas de 12 años de antiguedad ",
					" No podemos atender un vehiculo con mas de 12 años de antiguedad ");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Vehiculo.class.isAssignableFrom(clazz);
	}

	
}
