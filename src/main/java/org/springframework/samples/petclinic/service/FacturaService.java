package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.FacturaRepository;

public class FacturaService {
	
	private FacturaRepository facturaRepository;
	

	@Autowired
	public FacturaService(FacturaRepository facturaRepository) {
		this.facturaRepository = facturaRepository; 
	}	

}
