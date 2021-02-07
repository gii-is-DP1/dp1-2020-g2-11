package org.springframework.samples.petclinic.web;
import org.springframework.samples.petclinic.model.Mecanico;
import org.springframework.samples.petclinic.service.MecanicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class MecanicoController {
	
	private MecanicoService mecanicoService;
	
	@Autowired
	public MecanicoController(MecanicoService mecanicoService) {
		this.mecanicoService= mecanicoService;
	}
	
	@GetMapping(value = { "/mecanicos" })
	public String findAllMecanicos(Map<String, Object> model) {
		Collection<Mecanico> mecanico = mecanicoService.findMecanico();
		model.put("selections", mecanico);
		return "mecanicos/ListaMecanicos";
	}
	@GetMapping(value = "/mecanicos/new")
	public String initCreationForm(Map<String, Object> model) {
		Mecanico mecanico = new Mecanico();
		model.put("mecanico", mecanico);
		return "mecanicos/FormularioMecanico";
	}

	@PostMapping(value = "/mecanicos/new")
	public String processCreationForm(@Valid Mecanico mecanico, BindingResult result,ModelMap map) {
		if (result.hasErrors()) {
			map.put("mecanico", mecanico);
			return "mecanicos/FormularioMecanico";
		}
		else {
			this.mecanicoService.saveMecanico(mecanico);
			return "redirect:/mecanicos";
		}
	}
}
