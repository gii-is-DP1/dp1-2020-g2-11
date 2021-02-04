package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedVehiculoException;
import org.springframework.samples.petclinic.service.exceptions.VehiculosAntiguo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VehiculoController {

	private VehiculoService vehiculoService;

	@Autowired
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

	@GetMapping(value = "/vehiculo/new")
	public String initCreationForm(ModelMap model) {
		Vehiculo vehiculo = new Vehiculo();
		model.put("vehiculo", vehiculo);
		return "vehiculos/formularioVehiculo";
	}

	@PostMapping(value = "/vehiculo/new")
	public String processCreationForm(Cliente cliente, @Valid Vehiculo vehiculo, BindingResult result, ModelMap model) throws DataAccessException, VehiculosAntiguo, DuplicatedVehiculoException {		
		if (result.hasErrors()) {
			model.put("vehiculo", vehiculo);
			return "vehiculos/formularioVehiculo";
		}
		else {
			UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = clienteDetails.getUsername();
			Cliente clienteRegistered = this.clienteService.findClienteByUsername(username);
			vehiculo.setCliente(clienteRegistered);
			this.vehiculoService.saveVehiculo(vehiculo);
            return "redirect:/vehiculo/"+vehiculo.getId();
		}
	}
	
	@GetMapping(value = { "/vehiculo/delete/{vehiculoId}" })
	public String deleteVehiculo(@PathVariable("vehiculoId") int vehiculoId, BindingResult res) {
		vehiculoService.deleteVehiculo(vehiculoId);
		return "redirect:/vehiculos";
	}
}
