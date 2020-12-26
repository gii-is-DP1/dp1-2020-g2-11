package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Reparacion;
import org.springframework.samples.petclinic.service.ReparacionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReparacionController {

	private ReparacionService reparacionService;
	
	@Autowired
	public ReparacionController(ReparacionService reparacionService) {
		this.reparacionService= reparacionService;
	}
	
	@GetMapping(value= {"/reparacion"})
	public String findAllReparaciones(Map<String,Object> model) {
		Collection<Reparacion> reparaciones= reparacionService.findReparaciones();
		model.put("selections", reparaciones);
		return "reparacion/ListaReparaciones";
	}
	
}
