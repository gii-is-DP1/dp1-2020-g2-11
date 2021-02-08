package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Reparacion;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ReparacionService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReparacionController {

	private ReparacionService reparacionService;
	
	@Autowired
	private final ClienteService clienteService;
	@Autowired
	private final VehiculoService vehiculoService;
	@Autowired
	public ReparacionController(ReparacionService reparacionService, ClienteService clienteService, VehiculoService vehiculoService) {
		this.reparacionService= reparacionService;
		this.clienteService=clienteService;
		this.vehiculoService=vehiculoService;
	}
	
	@GetMapping(value= {"/reparaciones"})
	public String findAllReparaciones(Map<String,Object> model) {
		Collection<Reparacion> reparaciones= reparacionService.findReparaciones();
		model.put("reparaciones", reparaciones);
		return "reparaciones/ListaReparaciones";
	}
	
	@GetMapping(value = { "/reparacion/{reparacionId}" })
	public String findById(@PathVariable("reparacionId") int reparacionId, ModelMap map) {
		Reparacion reparacion = reparacionService.findReparacionById(reparacionId).get();
		map.put("reparacion", reparacion);
		return "reparaciones/reparacionDetails";
	}
	
	@GetMapping(value = { "/reparacion/delete/{reparacionId}" })
	public String deleteReparacion(@PathVariable("reparacionId") int reparacionId) {
		reparacionService.deleteReparacion(reparacionId);
		return "redirect:/reparaciones";
	}
	
	@GetMapping(value = "/reparacion/new")
	public String initCreationForm(ModelMap model) {
		Reparacion reparacion = new Reparacion();
		String dni = "";
		String matricula = "";
		model.put("reparacion", reparacion);
		model.put("dni", dni);
		model.put("matricula", matricula);
		return "reparaciones/FormularioReparacion";
	}
	
	@PostMapping(value = "/reparacion/new")
	public String processCreationForm(@Valid Reparacion reparacion, BindingResult result,ModelMap model) throws DataAccessException {
		if (result.hasErrors()) {
			model.put("reparacion", reparacion);
			return "reparaciones/FormularioReparacion";
		}
		else {
			Cliente c = this.clienteService.findClienteByDni(reparacion.getCliente().getDni());
			Vehiculo v = this.vehiculoService.findVehiculoByMatricula(reparacion.getVehiculo().getMatricula());
			reparacion.setCliente(c);
			reparacion.setVehiculo(v);
			this.reparacionService.saveReparacion(reparacion);
			return "redirect:/reparaciones";
		}
	}
	
	@GetMapping("/reparacion/{reparacionId}/edit")
	public String initUpdateReparacionForm(@PathVariable("reparacionId") int reparacionId, Model model) {
		Reparacion reparacion = this.reparacionService.findReparacionById(reparacionId).get();
		model.addAttribute(reparacion);
		return "reparaciones/FormularioReparacion";
	}

	@PostMapping("/reparacion/{reparacionId}/edit")
	public String processUpdateOwnerForm(@Valid Reparacion reparacion, BindingResult result,
			@PathVariable("reparacionId") int reparacionId) {
		if (result.hasErrors()) {
			return "reparaciones/FormularioReparacion";
		}
		else {
			Cliente c = this.clienteService.findClienteByDni(reparacion.getCliente().getDni());
			Vehiculo v = this.vehiculoService.findVehiculoByMatricula(reparacion.getVehiculo().getMatricula());
			reparacion.setCliente(c);
			reparacion.setVehiculo(v);
			reparacion.setId(reparacionId);
			this.reparacionService.saveReparacion(reparacion);
			return "redirect:/reparaciones";
		}
	}
}
