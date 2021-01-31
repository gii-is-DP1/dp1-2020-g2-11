package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ReparacionService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class VehiculoController {
	
	private ReparacionService reparacionService;
	
	@Autowired
	public void ReparacionController(ReparacionService reparacionService, AuthoritiesService authoritiesService) {
		this.reparacionService = reparacionService;
	}

}
