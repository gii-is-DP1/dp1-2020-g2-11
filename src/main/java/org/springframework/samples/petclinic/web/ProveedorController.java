package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProveedorController {
	
	private ProveedorService proveedorService;

	@Autowired
	public ProveedorController(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	@GetMapping(value = { "/proveedores" })
	public String findAllproveedores(ModelMap modelMap) {
		Collection<Proveedor> proveedores = proveedorService.findProveedores();
		modelMap.put("selections", proveedores);
		return "proveedores/ListaProveedores";
	}

	@GetMapping(value = { "/proveedor" })
	public String findproveedorsByNombre(Proveedor proveedor, BindingResult res, ModelMap modelMap) {
		if (proveedor.getNombre() == null) {
			proveedor.setNombre(""); // empty string signifies broadest possible search
		}
		// find proveedors by name
		Collection<Proveedor> results = this.proveedorService.findProveedorByNombre(proveedor.getNombre());
		if (results.isEmpty()) {
			// no proveedors found
			res.rejectValue("nombre", "notFound", "not found");
			return "proveedores/findProveedores";
		} else if (results.size() == 1) {
			// 1 proveedor found
			proveedor = results.iterator().next();
			return "redirect:/proveedores/" + proveedor.getId();
		} else {
			// multiple proveedors found
			modelMap.put("selections", results);
			return "proveedores/ListaProveedores";
		}
	}

}
