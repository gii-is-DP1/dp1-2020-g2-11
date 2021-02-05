package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Estancia;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.EstanciaService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.samples.petclinic.service.exceptions.FechasIncorrectas;
import org.springframework.samples.petclinic.service.exceptions.SobrecargaDeVehiculosException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EstanciaController {
	private EstanciaService estanciaService;

	@Autowired
	private final VehiculoService vehiculoService;
	
	@Autowired
	public EstanciaController(EstanciaService estanciaService, VehiculoService vehiculoService) {
		this.estanciaService = estanciaService;
		this.vehiculoService=vehiculoService;
	}

	@GetMapping(value = { "/estancias" })
	public String findAllEstancias(ModelMap model) {
		Collection<Estancia> estancias = estanciaService.findAllEstancia();
		model.put("estancias", estancias);
		return "estancias/ListaEstancias";
	}
	
	@GetMapping(value = { "/estancia/new" })
	public String createEstancia(ModelMap model) {
		Estancia estancia=new Estancia();
		String matricula ="";
		model.put("estancia", estancia);
		model.put("matricula", matricula);
		return "estancias/FormularioEstancias";
	}
	@PostMapping(value = "/estancia/new")
	public String processCreationForm(@Valid Estancia estancia, BindingResult result,ModelMap model) throws DataAccessException, SobrecargaDeVehiculosException, FechasIncorrectas {
		if (result.hasErrors()) {
			model.put("estancia", estancia);
			return "estancia/FormularioEstancias";
		}
		else {
			Vehiculo v = this.vehiculoService.findVehiculoByMatricula(estancia.getVehiculo().getMatricula());
			estancia.setVehiculo(v);
			this.estanciaService.saveEstancia(estancia);
			return "redirect:/estancias";
		}
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

//	@GetMapping(value = { "/estanciasactuales" })
//	public String findEstanciasActuales(Estancia estancia, BindingResult res, Map<String, Object> model) {
//		if (estancia.getFechaEntrada() == null) {
//			estancia.setFechaSalida(LocalDate.parse("")); // empty string signifies broadest possible search
//		}
//		Collection<Estancia> results= this.estanciaService.findEstanciaByFechaEstancia(estancia.getFechaEntrada());
//		if (results.isEmpty()) {
//			res.rejectValue("fecha", "notFound", "not found");
//			return "estancias/findEstancias";
//		}else if(results.size()==1) {
//			estancia= results.iterator().next();
//			return  "redirect:/estancia/" +estancia.getId();
//		}else {
//			model.put("estancia2", results);
//			return "estancia/findEstancias";
//		}				
//	}

	@GetMapping(value = { "/estanciasactuales" })
	public String findEstanciasActuales(LocalDate fecha, Estancia estancia, BindingResult res, ModelMap modelMap) {
		if (estancia.getFechaEntrada() == null) {
			Collection<Estancia> results = this.estanciaService.findAllEstancia();
			modelMap.addAttribute("estancia2",results);
			return "estancias/findEstancias";
		} else {
			Collection<Estancia> results = this.estanciaService.findEstanciaByFechaEstancia(fecha);
			if (results.isEmpty()) {
				res.rejectValue("fecha", "notFound", "not found");
				return "estancias/findEstancias";
			} else if (results.size() == 1) {
				estancia = results.iterator().next();
				return "redirect:/estancias/" + estancia.getId();
			} else {
				modelMap.addAttribute("estancia2", results);
				return "estancias/findEstancias";
			}
		}
	
	
	}
	
	@GetMapping(value = { "/estancias/delete/{estanciaId}" })
	public String deleteEstancia(@PathVariable("estanciaId") int estanciaId) {
		estanciaService.deleteEstancia(estanciaId);
		return "redirect:/estancias";
	}
	
	
	
	
	
	
	
	
}
