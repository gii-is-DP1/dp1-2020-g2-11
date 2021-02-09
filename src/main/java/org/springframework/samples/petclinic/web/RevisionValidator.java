package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import org.springframework.samples.petclinic.model.Revision;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RevisionValidator implements Validator {

	@Override
	public void validate(Object obj, Errors errors) {

		Revision revision = (Revision) obj;
		LocalDate fechaRevision = revision.getFechaRevision();
		String descripcion = revision.getDescripcion();
		Integer duracion = revision.getDuracion();
		String cliente = revision.getCliente().getDni();
		
		
		if(fechaRevision == null) {
			errors.rejectValue("fechaRevision", "este campo no puede estar vacio", "este campo no puede estar vacio");
		}else if(fechaRevision.isAfter(LocalDate.now())) {
			errors.rejectValue("fechaRevision", "la fecha de revisión debe ser anterior a la fecha actual", "la fecha de revisión debe ser anterior a la fecha actual");
		}
		
		if(descripcion == null) {
			errors.rejectValue("descripcion", "este campo no puede estar vacio", "este campo no puede estar vacio");
		}
		if(duracion == null) {
			errors.rejectValue("duracion", "este campo no puede estar vacio", "este campo no puede estar vacio");
		}
		
		if(cliente == null) {
			errors.rejectValue("cliente", "este campo no puede estar vacio", "este campo no puede estar vacio");
		}
		
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Revision.class.isAssignableFrom(clazz);
	}
}
