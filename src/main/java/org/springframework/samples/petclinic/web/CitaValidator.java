package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CitaValidator implements Validator {

	@Override
	public void validate(Object obj, Errors errors) {

		Cita cita = (Cita) obj;
		LocalDate fechaCita = cita.getFechaCita();
		LocalTime horaCita = cita.getHoraCita();
		Cliente cliente = cita.getCliente();
		Vehiculo vehiculo = cita.getVehiculo();

		if (fechaCita.isBefore(LocalDate.now())) {
			errors.rejectValue("fechaCita", " debe ser posterior a hoy",
					" debe ser posterior a hoy");
		}
		
		if (!(horaCita.isAfter(LocalTime.of(8, 00)) && horaCita.isBefore(LocalTime.of(16, 00)))) {
			errors.rejectValue("horaCita",  " debe estar entre las 8:00 y las 16:00",
					 " debe estar entre las 8:00 y las 16:00");
		}
		if (vehiculo.getCliente()!=cliente) {
			errors.rejectValue("horaCita",  " No puedes crear una cita con un coche que pertenece a otro cliente",
					 " No puedes crear una cita con un coche que pertenece a otro cliente");
		}

	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Cita.class.isAssignableFrom(clazz);
	}
}
