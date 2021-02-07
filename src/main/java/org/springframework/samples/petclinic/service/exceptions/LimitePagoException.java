package org.springframework.samples.petclinic.service.exceptions;

public class LimitePagoException extends Exception {
	private static final long serialVersionUID = 1L;
	public LimitePagoException(String message) {
		super(message);
	}

}
