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

		if (fechaRevision.isAfter(LocalDate.now())) {
			errors.rejectValue("fechaRevision", "la fecha de revisión debe ser anterior a la fecha actual", "la fecha de revisión debe ser anterior a la fecha actual");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Revision.class.isAssignableFrom(clazz);
	}
}
