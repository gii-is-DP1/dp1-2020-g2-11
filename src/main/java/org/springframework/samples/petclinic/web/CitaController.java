package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.service.CitaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CitaController {
	@Autowired
	private CitaService citaService;
	@Autowired
	private ClienteService clienteService;

	@Autowired
	public CitaController(CitaService citaService) {
		this.citaService = citaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = { "/citas" })
	public String findAllCitas(Map<String, Object> model) {
		Collection<Cita> citas = citaService.findAllCita();
		model.put("selections", citas);
		return "cita/ListaCitas";
	}

	@GetMapping(value = { "/cita" })
	public String findcitasByFecha(LocalDate fecha, BindingResult res, Map<String, Object> model) {
		// buscamos citas por fecha
		Collection<Cita> results = this.citaService.findCitaByFechaCita(fecha);
		if (results.isEmpty()) {
			// no hay encontramos citas
			res.rejectValue("fechaCita", "notFound", "not found");
			return "cita/findCitas";
		} else if (results.size() == 1) {
			// 1 cita encontrada
			Cita cita = results.iterator().next();
			return "redirect:/cita/" + cita.getId();
		} else {
			// varias citas encontradas
			model.put("selections", results);
			return "cita/ListaCitas";
		}
	}

	@GetMapping(value = "/cliente/{clienteId}/cita/new")
	public String initCreationForm(@PathVariable("clienteId") int clienteId, ModelMap model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = clienteDetails.getUsername();
		Cliente clienteRegistered = this.clienteService.findClienteByUsername(username);
		if (clienteRegistered.getId() != clienteId) {
			String message = "No puedes crear una cita por otro";
			model.put("customMessage", message);
			return "exception";
		}
		Cita cita = new Cita();
		model.put("selections", cita);
		return "cita/FormularioCita";
	}

}
