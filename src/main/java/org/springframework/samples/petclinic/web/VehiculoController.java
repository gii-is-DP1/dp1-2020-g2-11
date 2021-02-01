package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VehiculoController {
	
	private VehiculoService vehiculoService;
	
	@Autowired
	public VehiculoController(VehiculoService vehiculoService, AuthoritiesService authoritiesService) {
		this.vehiculoService = vehiculoService;
	}
@GetMapping(value={"/vehiculos"})
public String findAllVehiculos(Map<String, Object> model) {
	Collection<Vehiculo> vehiculos = vehiculoService.findVehiculos();
	model.put("selections", vehiculos);
	return"vehiculos/ListaVehiculos";
}

@GetMapping(value= {"/vehiculos"})
public String findVehiculoByTipo(TipoVehiculo tp ,BindingResult res,Map<String, Object> model) {
	
	if(tp==null) { 
		Collection<Vehiculo> vehiculos = vehiculoService.findVehiculos();
		model.put("selections", vehiculos);
	}
	else {
		
	Collection<Vehiculo> results=vehiculoService.findVehículoByTipo(tp);
	if(results.isEmpty()) {
		res.rejectValue("tipoVehiculo","notFound",  "not found");
	}
	else {
		model.put("selections", results);
	}
	}
	return "proveedor/ListaVehiculos";
	
}
@GetMapping(value= {"/vehiculos"})
public String findVEhiculosByMatricula(String matricula,BindingResult res, Map<String, Object> model) {
	
	if(matricula==null) { 
	Collection<Vehiculo> vehiculos = vehiculoService.findVehiculos();
	model.put("selections", vehiculos);
	return "vehiculo/listaVehiculos";
	}
	else {
		Collection<Vehiculo> results=vehiculoService.findVehículoByMatricula(matricula);
		if (results.isEmpty()) {
			res.rejectValue("matricula","notFound",  "not found");
			return "vehiculo/listaVehiculos";
		}
		else if (results.size()==1){
			Vehiculo vehiculo= results.iterator().next();
			return "redirect:/vehiculo/"+vehiculo.getMatricula();
		}
		else {
			model.put("selections", results);
			return "vehiculo/listaVehiculos";
		}
	}

}

}

