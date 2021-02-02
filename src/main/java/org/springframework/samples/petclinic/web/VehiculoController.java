package org.springframework.samples.petclinic.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VehiculoController {

	private VehiculoService vehiculoService;

	@Autowired
	public VehiculoController(VehiculoService vehiculoService, AuthoritiesService authoritiesService) {
		this.vehiculoService = vehiculoService;
	}

	@GetMapping(value = { "/vehiculos" })
	public String findAllVehiculos(ModelMap model) {
		Collection<Vehiculo> vehiculos = vehiculoService.findVehiculos();
		model.put("vehiculos", vehiculos);
		return "vehiculos/ListaVehiculos";
	}

	@GetMapping(value = { "/vehiculo/tipo" })
	public String findVehiculoByTipo(String tpo, BindingResult res, ModelMap model) {

		if (tpo == null || tpo == "") {
			Collection<Vehiculo> vehiculos = vehiculoService.findVehiculos();
			model.put("vehiculos", vehiculos);
		} else {
			TipoVehiculo tp = TipoVehiculo.valueOf(tpo);
			Collection<Vehiculo> results = vehiculoService.findVehículoByTipo(tp);
			if (results.isEmpty()) {
				res.rejectValue("tipoVehiculo", "notFound", "not found");
			} else {
				model.put("vehiculos", results);
			}
		}
		return "proveedor/ListaVehiculos";

	}

	@GetMapping(value = { "/vehiculo/{vehiculoId}/details" })
	public String findVehiculosByMatricula(@PathVariable("vehiculoId") int vehiculoId, String matricula,
			BindingResult res, ModelMap model) {
		if (matricula == null) {
			Collection<Vehiculo> vehiculos = vehiculoService.findVehiculos();
			model.put("vehiculos", vehiculos);
			return "vehiculo/listaVehiculos";
		} else {
			Collection<Vehiculo> results = vehiculoService.findVehículoByMatricula(matricula);
			if (results.isEmpty()) {
				res.rejectValue("matricula", "notFound", "not found");
				return "vehiculo/listaVehiculos";
			} else if (results.size() == 1) {
				Vehiculo vehiculo = results.iterator().next();
				return "redirect:/vehiculo/" + vehiculoId + "/details";
			} else {
				model.put("vehiculos", results);
				return "vehiculo/listaVehiculos";
			}
		}

	}

}
