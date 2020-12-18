package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ReparacionService;
import org.springframework.samples.petclinic.service.UserService;

public class VehiculoController {

	private static final String VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM = "";
	
	private ReparacionService repService;
	
	@Autowired
	public void ReparacionController(ReparacionService repService, AuthoritiesService authoritiesService) {
		this.repService = repService;
	}

}
