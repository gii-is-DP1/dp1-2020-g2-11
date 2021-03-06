package org.springframework.samples.petclinic.web;

import javax.swing.text.html.ListView;
import javax.validation.Valid;
import java.util.*; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VehiculoController {

	private VehiculoService vehiculoService;

	@Autowired
	private final ClienteService clienteService;

	@Autowired
	public VehiculoController(ClienteService clienteService, VehiculoService vehiculoService,
			AuthoritiesService authoritiesService) {
		this.vehiculoService = vehiculoService;
		this.clienteService = clienteService;
	}

	@InitBinder("vehiculo")
    public void initPetBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new VehiculoValidator());
    }
	
	@GetMapping(value = { "/vehiculos" })
	public String findVehiculos(Map<String, Object> model) {
		Collection<TipoVehiculo> l=(Arrays.asList(TipoVehiculo.values()));
		model.put("tipoVehiculo", l);
		Collection<Vehiculo> vehiculos = vehiculoService.findVehiculos();
		Vehiculo vehiculo = new Vehiculo();
		model.put("vehiculo", vehiculo);
		model.put("vehiculos", vehiculos);
		return "vehiculos/ListaVehiculos";
	}

	@GetMapping(value = "/vehiculos/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("vehiculo", new Vehiculo());
		return "vehiculos/ListaVehiculos";
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
		Collection<TipoVehiculo> l=(Arrays.asList(TipoVehiculo.values()));
		model.put("tipoVehiculo", l);
		model.put("vehiculo", vehiculo);
		return "vehiculos/formularioVehiculo";
	}

	@PostMapping(value = "/vehiculo/new")
	public String processCreationForm(@Valid Vehiculo vehiculo, BindingResult result, ModelMap model)
			throws DataAccessException, VehiculosAntiguo, DuplicatedVehiculoException {
		Collection<TipoVehiculo> l=(Arrays.asList(TipoVehiculo.values()));
		model.put("tipoVehiculo", l);
		if (result.hasErrors()) {
			model.put("vehiculo", vehiculo);
			return "vehiculos/formularioVehiculo";
		} else {
			UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = clienteDetails.getUsername();
			Cliente clienteRegistered = this.clienteService.findClienteByUsername(username);
			vehiculo.setCliente(clienteRegistered);
			this.vehiculoService.saveVehiculo(vehiculo);
			return "redirect:/vehiculo/" + vehiculo.getId();
		}
	}


	@GetMapping("/vehiculo/{vehiculoId}/edit")
	public String initUpdateVehicleForm(@PathVariable("vehiculoId") int vehiculoId, ModelMap model) {
		Collection<TipoVehiculo> l=(Arrays.asList(TipoVehiculo.values()));
		model.put("tipoVehiculo", l);
		Collection<Cliente> clientes = clienteService.findClientes();
		model.put("cliente", clientes);
		Vehiculo vehiculo = this.vehiculoService.findVehiculoById(vehiculoId);
		model.put("vehiculo",vehiculo);
		
		vehiculo.setId(vehiculoId);
		
		return "vehiculos/formularioVehiculo";
	}

	@PostMapping("/vehiculo/{vehiculoId}/edit")
	public String processUpdateVehicleForm(@Valid Vehiculo vehiculo, BindingResult result, ModelMap model,
			@PathVariable("vehiculoId") int vehiculoId) throws VehiculosAntiguo {
		Collection<TipoVehiculo> l=(Arrays.asList(TipoVehiculo.values()));
		model.put("tipoVehiculo", l);
		Collection<Cliente> clientes = clienteService.findClientes();
		model.put("cliente", clientes);
		if (result.hasErrors()) {
			model.put("vehiculo", vehiculo);
			return "vehiculos/formularioVehiculo";
		} else {
			vehiculo.setId(vehiculoId);
			UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = clienteDetails.getUsername();
			Cliente clienteRegistered = this.clienteService.findClienteByUsername(username);
			vehiculo.setCliente(clienteRegistered);
			this.vehiculoService.saveVehiculo(vehiculo);
			return "redirect:/vehiculo/{vehiculoId}";
		}
	}
	
	@GetMapping("/vehiculos/{vehiculoId}/edit")
	public String initUpdateVehicleForm2(@PathVariable("vehiculoId") int vehiculoId, ModelMap model) {
		Vehiculo vehiculo = this.vehiculoService.findVehiculoById(vehiculoId);
		Collection<TipoVehiculo> l=(Arrays.asList(TipoVehiculo.values()));
		model.put("tipoVehiculo", l);
		Collection<Cliente> clienteRegistered = this.clienteService.findClientes();
		model.put("cliente", clienteRegistered);
		model.addAttribute("vehiculo" ,vehiculo);
		return "vehiculos/formularioVehiculo";
	}

	@PostMapping("/vehiculos/{vehiculoId}/edit")
	public String processUpdateVehicleForm2(@Valid Vehiculo vehiculo, BindingResult result, ModelMap model,
			@PathVariable("vehiculoId") int vehiculoId) throws VehiculosAntiguo {
		Collection<TipoVehiculo> l=(Arrays.asList(TipoVehiculo.values()));
		model.put("tipoVehiculo", l);
		Collection<Cliente> clienteRegistered = this.clienteService.findClientes();
		model.put("cliente", clienteRegistered);
		if (result.hasErrors()) {
			model.put("vehiculo", vehiculo);
			
			return "vehiculos/formularioVehiculo";
		} else {
			vehiculo.setId(vehiculoId);
			Cliente cliente =clienteService.findClienteByDni(vehiculo.getCliente().getDni());
			vehiculo.setCliente(cliente);
			this.vehiculoService.saveVehiculo(vehiculo);
			return "redirect:/vehiculo/{vehiculoId}";
		}
	}
}
