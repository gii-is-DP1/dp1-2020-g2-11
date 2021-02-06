package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.CitaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.samples.petclinic.service.exceptions.SobrecargaDeVehiculosException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CitaController {

	
	private final CitaService citaService;
	@Autowired
	private final ClienteService clienteService;
	@Autowired
	private final VehiculoService vehiculoService;

	@Autowired
	public CitaController(CitaService citaService, ClienteService clienteService,VehiculoService vehiculoService) {
		this.citaService = citaService;
		this.clienteService = clienteService;
		this.vehiculoService = vehiculoService;
	}

//	@InitBinder
//	public void setAllowedFields(WebDataBinder dataBinder) {
//		dataBinder.setDisallowedFields("id");
//	}

	@GetMapping(value = { "/citas" })
	public String findAllCitas(Map<String, Object> model) {
		Collection<Cita> citas = citaService.findAllCita();
		Cita cita = new Cita();
		model.put("cita", cita);
		model.put("citas", citas);
		return "citas/ListaCitas";
	}
	
	@GetMapping(value = "/citas/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("cita", new Cita());
		return "citas/findCitas";
	}

	@PostMapping(value = { "/citas" })
	public String findcitasByFecha(@Valid Cita cita, BindingResult res, Map<String, Object> model) {
		if (cita.getFechaCita() == null) {
			 // empty string signifies broadest possible search
			return "redirect:/citas";
		}
		Collection<Cita> citas = this.citaService.findCitaByFechaCita(cita.getFechaCita());
		if (citas.isEmpty()) {
			// no clientes found
			res.rejectValue("fechaCita", "notFound", "not found");
			return "redirect:/citas/find";
		} else {
			// buscamos citas por fecha
		model.put("citas", citas);
		return "citas/ListaCitas";
		}
		
	}

	@GetMapping(value = "/cliente/{clienteId}/cita/new")
	public String initCreationForm(@PathVariable("clienteId") int clienteId, ModelMap model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = clienteDetails.getUsername();
		Optional<Cliente> clienteRegistered = this.clienteService.findClienteById(clienteId);
		if (clienteRegistered.get().getId() == clienteId) {
			String message = "No puedes crear una cita por otro";
			model.put("customMessage", message);
			return "exception";
		}
		Cita cita = new Cita();
		
		model.put("cita", cita);
		return "citas/FormularioCita";
	}

	@PostMapping(value = "/cliente/{clienteId}/cita/new")
	public String processCreationForm(@Valid Cita cita, BindingResult result,
			@PathVariable("clienteId") int citaId, ModelMap model) throws DataAccessException, SobrecargaDeVehiculosException {
		if (result.hasErrors()) {
			model.put("cita", cita);
			return "citas/FormularioCita";
		} else {
			model.put("cita", cita);
			UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = clienteDetails.getUsername();
			Cliente clienteRegistered = this.clienteService.findClienteByUsername(username);
			cita.setCliente(clienteRegistered);
			this.citaService.saveCita(cita);
			return "redirect:/citas";
		}
	}	
	
	@GetMapping(value = "/citas/new")
	public String initCreationForm2( ModelMap model) {
		Cita cita = new Cita();
		String dni = "";
		String matricula = "";
		model.put("cita", cita);
		model.put("dni", dni);
		model.put("matricula", matricula);
		return "citas/FormularioCitaByAdmin";
	}
	
	@PostMapping(value = "/citas/new")
	public String processCreationForm2(@Valid Cita cita, BindingResult result, ModelMap model) throws DataAccessException, SobrecargaDeVehiculosException {
		if (result.hasErrors()) {
			model.put("cita", cita);
			return "citas/FormularioCitaByAdmin";
			
		} else {
			//model.put("cita", cita);
			Cliente c = this.clienteService.findClienteByDni(cita.getCliente().getDni());
			cita.setCliente(c);
			Vehiculo v = this.vehiculoService.findVehiculoByMatricula(cita.getVehiculo().getMatricula());
			cita.setVehiculo(v);
			this.citaService.saveCita(cita);
			return "redirect:/citas";
			}
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
