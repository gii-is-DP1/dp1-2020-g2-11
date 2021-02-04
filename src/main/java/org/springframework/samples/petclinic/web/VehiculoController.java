package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VehiculoController {

	private VehiculoService vehiculoService;
	private ClienteService clienteService;

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
			Collection<Vehiculo> results = vehiculoService.findVehiculoByTipo(tp);
			if (results.isEmpty()) {
				res.rejectValue("tipoVehiculo", "notFound", "not found");
			} else {
				model.put("vehiculos", results);
			}
		}
		return "proveedor/ListaVehiculos";
	}

	@GetMapping(value = "/vehiculos/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("vehiculo", new Vehiculo());
		return "vehiculos/findVehiculos";
	}

	@GetMapping(value = { "/vehiculo/{vehiculoId}" })
	public String findVehiculosById(@PathVariable("vehiculoId") int vehiculoId, ModelMap model) {
		Vehiculo vehiculo = vehiculoService.findVehiculoById(vehiculoId);
		model.put("vehiculo", vehiculo);
		return "vehiculos/vehiculoDetails";
	}

	@GetMapping(value = { "/vehiculos/cliente" })
	public String findMisVehiculos(ModelMap model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Cliente cliente = clienteService.findClienteByUsername(clienteDetails.getUsername());
		Integer clienteId = cliente.getId();
		Collection<Vehiculo> vehiculos = vehiculoService.findVehiculoByCliente(clienteId);
		model.put("vehiculos", vehiculos);
		return "vehiculos/ListaVehiculos";
	}

}
