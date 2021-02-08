package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProveedorController {
	
	private ProveedorService proveedorService;

	@Autowired
	public ProveedorController(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	@GetMapping(value = { "/proveedores" })
	public String findAllproveedores(ModelMap modelMap) {
		Collection<Proveedor> proveedores = proveedorService.findProveedoresDisponibles();
		modelMap.put("proveedor", proveedores);
		return "proveedores/ListaProveedores";
	}
	
	@GetMapping(value = { "/proveedores/proveedoresNoDisponibles" })
	public String findAllproveedoresNoDisponibles(ModelMap modelMap) {
		Collection<Proveedor> proveedores = proveedorService.findProveedoresNoDisponibles();
		modelMap.put("proveedor", proveedores);
		return "proveedores/ProveedoresNoDisponibles";
	}
	
	@GetMapping(value = { "/proveedor/{proveedorId}" })
	public String findProveedorById(@PathVariable("proveedorId") int proveedorId, ModelMap model) {
		Proveedor proveedor = this.proveedorService.findProveedorById(proveedorId);
		model.put("proveedor", proveedor);
		return "proveedores/proveedorDetails";
	}

	@GetMapping(value = { "/proveedorbynombre" })
	public String findproveedorsByNombre(String nombre, Proveedor proveedor, BindingResult res,
			Map<String, Object> model) {
		if (proveedor.getNombre() == null) {
			proveedor.setNombre(""); // empty string signifies broadest possible search
		}
		// find proveedors by name
		Proveedor results = this.proveedorService.findProveedorByNombre(nombre);
		if (results==null) {
			// no proveedor found
			res.rejectValue("nombre", "notFound", "not found");
			return "proveedores/ListaProveedores";
			}
		else {
			// proveedor found
			model.put("proveedor", results);
			return "proveedores/ListaProveedores";
		}
	}

	@GetMapping(value = "/proveedores/new")
	public String initCreationForm(ModelMap model) {
		Proveedor proveedor = new Proveedor();
		model.put("proveedor", proveedor);
		return "proveedores/FormularioProveedor";
	}

	@PostMapping(value = "/proveedores/new")
	public String processCreationForm(@Valid Proveedor proveedor, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("proveedor", proveedor);
			return "proveedores/FormularioProveedor";
		} else {
			model.put("proveedor", proveedor);
			this.proveedorService.saveProveedor(proveedor);
			return "redirect:/proveedores";
		}
	}
	
	@GetMapping("/proveedor/{proveedorId}/edit")
	public String initUpdateProveedorForm(@PathVariable("proveedorId") int proveedorId, ModelMap model) {
		Proveedor proveedor = proveedorService.findProveedorById(proveedorId);
		model.put("proveedor",proveedor);
		return "proveedores/FormularioProveedor";
	}

	@PostMapping("/proveedor/{proveedorId}/edit")
	public String processUpdateProveedorForm(@Valid Proveedor proveedor, BindingResult result,  ModelMap model,
			@PathVariable("proveedorId") int proveedorId) {
		if (result.hasErrors()) {
			model.put("proveedor", proveedor);
			return "proveedores/FormularioProveedor";
		}
		else {
			proveedor.setId(proveedorId);
			this.proveedorService.saveProveedor(proveedor);
			return "redirect:/proveedor/{proveedorId}";
		}
	}
	
	@GetMapping(value = { "/proveedor/{proveedorId}/oculta" })
	public String ocultaProveedor(@PathVariable("proveedorId") Integer proveedorId) {
		proveedorService.ocultaProveedorDisponible(proveedorId);
		return "redirect:/proveedores";
	}
	
	@GetMapping(value = { "/proveedor/{proveedorId}/devuelve" })
	public String devuelveProveedor(@PathVariable("proveedorId") Integer proveedorId) {
		proveedorService.devuelveProveedorNoDisponible(proveedorId);
		return "redirect:/proveedores";
	}
}
