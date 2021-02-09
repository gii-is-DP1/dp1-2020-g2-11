package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.CitaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.samples.petclinic.service.exceptions.NotPropertyException;
import org.springframework.samples.petclinic.service.exceptions.SobrecargaDeVehiculosException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CitaController {
	
	private final CitaService citaService;
	
	private final ClienteService clienteService;
	
	private final VehiculoService vehiculoService;

	@Autowired
	public CitaController(CitaService citaService, ClienteService clienteService, VehiculoService vehiculoService) {
		this.citaService = citaService;
		this.clienteService = clienteService;
		this.vehiculoService = vehiculoService;
	}

	@InitBinder("cita")
	public void initPetBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new CitaValidator());
	}

	@GetMapping(value = { "/citas" })
	public String findAllCitas(Map<String, Object> model) {
		Collection<Cita> citas = citaService.findAllCita();
		Cita cita = new Cita();
		model.put("cita", cita);
		model.put("citas", citas);
		return "citas/ListaCitas";
	}

	@GetMapping(value = "/cita/new")
	public String initCreationForm(ModelMap model) {
		Cita cita = new Cita();
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username = clienteDetails.getUsername();
		Cliente clienteRegistered = clienteService.findClienteByUsername(username);
		if (clienteRegistered == null) {
			model.put("cita", cita);
			return "/";
		}
		Collection<Vehiculo> vehiculo = vehiculoService.findVehiculoByCliente(clienteRegistered.getId());
		model.put("vehiculo", vehiculo);
		model.put("cita", cita);
		return "citas/FormularioCita";
	}

	@PostMapping(value = "/cita/new")
	public String processCreationForm(@Valid Cita cita, BindingResult result, ModelMap model)
			throws DataAccessException, SobrecargaDeVehiculosException, NotPropertyException {

		if (result.hasErrors()) {

			model.put("cita", cita);
			return "redirect:/cita/new";
		}
		LocalDate fechaCita = cita.getFechaCita();
		int n_otherCitas = citaService.findCitaByFechaCita(fechaCita).size();
		
		if (n_otherCitas >= 12) {
			model.put("cita", cita);
			return "/citas/FormularioCitaDateError";

		}

		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		String username = clienteDetails.getUsername();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        ?String username = auth.getName();
		Cliente clienteRegistered = this.clienteService.findClienteByUsername(username.trim());
		Collection<Vehiculo> vehiculo = vehiculoService.findVehiculoByCliente(clienteRegistered.getId());
		model.put("vehiculo", vehiculo);
		Vehiculo vehiculos = vehiculoService.findVehiculoByMatricula(cita.getVehiculo().getMatricula());
		cita.setVehiculo(vehiculos);
		cita.setCliente(clienteRegistered);
		this.citaService.saveCita(cita);
		return "redirect:/citas/cliente";

	}

	@GetMapping(value = { "/cita/delete/{citaId}" })
	public String deleteCita(@PathVariable("citaId") int citaId) {
		citaService.removeCita(citaId);
		return "redirect:/citas";
	}

	@GetMapping(value = { "/citas/cliente" })
	public String findMisCitas(ModelMap model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Cliente cliente = clienteService.findClienteByUsername(clienteDetails.getUsername());
		Integer clienteId = cliente.getId();
		Collection<Cita> citas = citaService.findCitasByCliente(clienteId);
		model.put("citas", citas);
		return "citas/ListaCitas";
	}
}