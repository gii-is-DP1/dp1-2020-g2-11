package org.springframework.samples.petclinic.web;

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
import org.springframework.samples.petclinic.service.exceptions.SobrecargaDeVehiculosException;
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
		model.put("cita", cita);
		return "citas/FormularioCita";
	}

	@PostMapping(value = "/cita/new")
	public String processCreationForm(@Valid Cita cita, BindingResult result,
			 ModelMap model) throws DataAccessException, SobrecargaDeVehiculosException {
		if (result.hasErrors()) {
			model.put("cita", cita);
			return "citas/FormularioCita";
		} else {
			UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String username = clienteDetails.getUsername();
			Cliente clienteRegistered = this.clienteService.findClienteByUsername(username);
			Vehiculo vehiculo = this.vehiculoService.findVehiculoByMatricula(cita.getVehiculo().getMatricula());
			cita.setVehiculo(vehiculo);
			cita.setCliente(clienteRegistered);
			this.citaService.saveCita(cita);
			return "redirect:/citas/cliente";
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
