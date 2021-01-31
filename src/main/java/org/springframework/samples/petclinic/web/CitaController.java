package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.service.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
@Controller
public class CitaController {
	@Autowired
	private CitaService citaService;
	
	@Autowired
	public CitaController(CitaService citaService) {
		this.citaService = citaService;
	}
	
	@InitBinder
	public  void  setAllowedFields ( WebDataBinder  dataBinder ) {
		dataBinder.setDisallowedFields ("id");
	}
	
	@GetMapping(value = { "/citas" })
	public String findAllcitas(Map<String, Object> model, LocalDate fecha) {
		Collection<Cita> citas = citaService.findCitaByFechaCita(fecha);
		model.put("selections", citas);
		return "cita/ListaCitas";
	}
	
	@GetMapping(value = { "/cita" })
	public String findcitasByFecha(Cita cita, BindingResult res, Map<String, Object> model) {
		if (cita.getFechaCita() == null) {
			cita.setFechaCita(null); // empty string signifies broadest possible search
		}
		// find proveedors by name
		Collection<Cita> results = this.citaService.findCitaByFechaCita(cita.getFechaCita());
		if (results.isEmpty()) {
			// no proveedors found
			res.rejectValue("fechaCita", "notFound", "not found");
			return "cita/findCitas";
		} else if (results.size() == 1) {
			// 1 proveedor found
			cita = results.iterator().next();
			return "redirect:/cita/" + cita.getId();
		} else {
			// multiple proveedors found
			model.put("selections", results);
			return "cita/ListaCitas";
		}
	}

}
