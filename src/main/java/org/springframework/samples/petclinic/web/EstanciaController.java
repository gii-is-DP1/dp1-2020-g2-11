package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Estancia;
import org.springframework.samples.petclinic.service.EstanciaService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EstanciaController {
	private EstanciaService estanciaService;

	@Autowired
	public EstanciaController(EstanciaService citaService) {
		this.estanciaService = estanciaService;
	}

	@GetMapping(value = { "/estancias/find" })
	public String findAllEstancias(Map<String, Object> model) {
		Collection<Estancia> estancias = estanciaService.findEstanciasActuales();
		model.put("selections", estancias);
		return "estancias/findEstancias";
	}
	

	@GetMapping(value = { "/estanciafechachita" })
	public String findEstanciaByFechaCita(Estancia estancia, BindingResult res, Map<String, Object> model) {
		if (estancia.getFechaEntrada() == null) {
			estancia.setFechaSalida(LocalDate.parse("")); // empty string signifies broadest possible search
		}
		Collection<Estancia> results= this.estanciaService.findEstanciaByFechaEstancia(estancia.getFechaEntrada());
		if (results.isEmpty()) {
			res.rejectValue("fecha", "notFound", "not found");
			return "estancias/findEstancias";
		}else if(results.size()==1) {
			estancia= results.iterator().next();
			return  "redirect:/estancia/" +estancia.getId();
		}else {
			model.put("estancia1", results);
			return "estancias/findEstancias";
		}
	}	

	@GetMapping(value = { "/estanciasactuales" })
	public String findEstanciasActuales(Estancia estancia, BindingResult res, Map<String, Object> model) {
		if (estancia.getFechaEntrada() == null) {
			estancia.setFechaSalida(LocalDate.parse("")); // empty string signifies broadest possible search
		}
		Collection<Estancia> results= this.estanciaService.findEstanciaByFechaEstancia(estancia.getFechaEntrada());
		if (results.isEmpty()) {
			res.rejectValue("fecha", "notFound", "not found");
			return "estancias/findEstancias";
		}else if(results.size()==1) {
			estancia= results.iterator().next();
			return  "redirect:/estancia/" +estancia.getId();
		}else {
			model.put("estancia2", results);
			return "estancia/findEstancias";
		}			
	}

}
