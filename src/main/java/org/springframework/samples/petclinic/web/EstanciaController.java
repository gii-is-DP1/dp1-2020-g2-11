package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Estancia;
import org.springframework.samples.petclinic.service.EstanciaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EstanciaController {
	private EstanciaService estanciaService;

	@Autowired
	public EstanciaController(EstanciaService citaService) {
		this.estanciaService = estanciaService;
	}

	@GetMapping(value = { "/estancias" })
	public String findAllEstancias(Map<String, Object> model) {
		Collection<Estancia> estancias = estanciaService.findEstanciasActuales();
		model.put("selections", estancias);
		return "estancias/ListaEstancias";
	}

}
