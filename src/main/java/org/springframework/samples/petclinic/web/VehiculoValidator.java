package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VehiculoValidator implements Validator {

	@Override
	public void validate(Object obj, Errors errors) {

		Vehiculo vehiculo = (Vehiculo) obj;
		LocalDate fechaFabricacion = vehiculo.getFechaFabricacion();
		Integer kilometraje = vehiculo.getKilometraje();
		String matricula = vehiculo.getMatricula();
		if (fechaFabricacion == null) {
			errors.rejectValue("fechaFabricacion", "Este campo no puede estar vacio", "Este campo no puede estar vacio");
		}
		else if ((LocalDate.now().getYear() - fechaFabricacion.getYear() >= 12) || (fechaFabricacion.equals(null))) {
			errors.rejectValue("fechaFabricacion", " No podemos atender un vehiculo con mas de 12 años de antiguedad ",
					" No podemos atender un vehiculo con mas de 12 años de antiguedad ");
		}

		else if (fechaFabricacion.isAfter(LocalDate.now())) {
			errors.rejectValue("fechaFabricacion", " La fecha de fabricación no puede ser posterior a la de hoy ",
					" La fecha de fabricación no puede ser posterior a la de hoy ");
		}
		
		if (kilometraje == null) {
			errors.rejectValue("kilometraje", "Este campo no puede estar vacio", "Este campo no puede estar vacio");
		}

		if (matricula.startsWith(" ") || matricula.isEmpty()) {
			errors.rejectValue("matricula", "Este campo no puede estar vacio", "Este campo no puede estar vacio");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Vehiculo.class.isAssignableFrom(clazz);
	}

}
