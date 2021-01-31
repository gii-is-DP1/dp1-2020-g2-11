package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.service.FacturaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacturaController {
private FacturaService facturaService;
	
	@Autowired
	public FacturaController(FacturaService facturaService) {
		this.facturaService = facturaService;
	}
	
	@GetMapping(value= {"/factura"})
	public String findAllFacturas(Map<String, Object> model) {
		Collection<Factura> facturas = facturaService.findFacturas();
		model.put("selections", facturas);
		return "factura/ListaFacturas";
	}

}
