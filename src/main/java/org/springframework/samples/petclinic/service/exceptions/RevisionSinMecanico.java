package org.springframework.samples.petclinic.service.exceptions;

public class RevisionSinMecanico extends Exception {
	
	public RevisionSinMecanico(String message) {
		super(message);
	}
}
