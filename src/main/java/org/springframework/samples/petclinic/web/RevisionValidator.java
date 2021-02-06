package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import org.springframework.samples.petclinic.model.Revision;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RevisionValidator implements Validator {

	@Override
	public void validate(Object obj, Errors errors) {

		Revision revision = (Revision) obj;
		LocalDate fechaRev = revision.getFechaRevision();

		if (fechaRev.isAfter(LocalDate.now())) {
			errors.rejectValue("fechaRev", "se debe rellenar a posteriori de realizar dicha revision",
					"se debe rellenar a posteriori de realizar dicha revision");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
