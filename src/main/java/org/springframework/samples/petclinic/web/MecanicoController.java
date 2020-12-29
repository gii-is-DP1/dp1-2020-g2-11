package org.springframework.samples.petclinic.web;
import org.springframework.samples.petclinic.model.Mecanico;
import org.springframework.samples.petclinic.service.MecanicoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Collection;
import java.util.Map;
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
		return "mecanico/ListaMecanicos";
	}
}
