package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.AuthoritiesService;

import org.springframework.samples.petclinic.service.VehiculoService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VehiculoController {
	
	private VehiculoService vehiculoService;
	
	@Autowired
	public VehiculoController(VehiculoService vehiculoService, AuthoritiesService authoritiesService) {
		this.vehiculoService = vehiculoService;
	}
@GetMapping(value={""})
public String findAllVehiculos(Map<String, Object> model) {
	Collection<Vehiculo> vehiculos = vehiculo
}
}

