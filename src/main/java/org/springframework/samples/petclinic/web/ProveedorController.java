package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

public class ProveedorController {
	
	private ProveedorService proveedorService;

	@Autowired
	public ProveedorController(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	@GetMapping(value = { "/proveedor" })
	public String findAllproveedors(Map<String, Object> model) {
		Collection<Proveedor> proveedores = proveedorService.findProveedores();
		model.put("selections", proveedores);
		return "proveedor/ListaProveedores";
	}

	@GetMapping(value = { "/proveedor" })
	public String findproveedorsByNombre(Proveedor proveedor, BindingResult res, Map<String, Object> model) {
		if (proveedor.getNombre() == null) {
			proveedor.setNombre(""); // empty string signifies broadest possible search
		}
		// find proveedors by name
		Collection<Proveedor> results = this.proveedorService.findProveedorByNombre(proveedor.getNombre());
		if (results.isEmpty()) {
			// no proveedors found
			res.rejectValue("nombre", "notFound", "not found");
			return "proveedor/findProveedores";
		} else if (results.size() == 1) {
			// 1 proveedor found
			proveedor = results.iterator().next();
			return "redirect:/proveedor/" + proveedor.getId();
		} else {
			// multiple proveedors found
			model.put("selections", results);
			return "proveedor/ListaProveedores";
		}
	}

}
