package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Revision;
import org.springframework.samples.petclinic.service.FacturaService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacturaController {
private FacturaService facturaService;
	
	@Autowired
	public FacturaController(FacturaService facturaService) {
		this.facturaService = facturaService;
	}
	
	@GetMapping(value= {"/facturas"})
	public String findAllFacturas(Map<String, Object> model) {
		Collection<Factura> facturas = facturaService.findFacturas();
		model.put("selections", facturas);
		return "facturas/ListaFacturas";
	}
	
	@GetMapping(value = { "/facturaId" })
	public String findFacturaById(Factura factura, BindingResult res, Map<String, Object> model) {
		if (factura.getId() == null) {
			factura.setId(null); // empty string signifies broadest possible search
		}
		// find facturas by id
		Optional<Factura> results = this.facturaService.findFacturabyId(factura.getId());
		if (results == null) {
			// no factura found
			res.rejectValue("id", "notFound", "not found");
			return "factura/ListaFacturas";
		
		} else {
			model.put("selections", results);
			return "factura/ListaFacturas";
		}
	}
	
	@GetMapping(value= {"/facturaFechaEmision"})
	public String findFacturaByFechaEmision(Factura factura, BindingResult res, Map<String, Object> model, LocalDate fecha) {
		if (factura.getFechaEmision() == null) {
			factura.setFechaEmision(LocalDate.parse("")); // empty string signifies broadest possible search
		}
		Collection<Factura> results= this.facturaService.findFacturabyFechaEmision(fecha);
		if (results.isEmpty()) {
			res.rejectValue("fecha", "notFound", "not found");
			return "facturas/findFacturas";
		}else if(results.size()==1) {
			factura= results.iterator().next();
			return  "redirect:/factura/" +factura.getId();
		}else {
			model.put("selections", results);
			return "factura/ListaFacturas";
		}
	}
	
	@GetMapping(value= {"/facturaPago"})
	public String findFacturaPagado(Factura factura, Boolean pago, BindingResult res, Map<String, Object> model) {
		Collection<Factura> results = this.facturaService.findFacturaPagado(pago);
		if (results.isEmpty()) {
			res.rejectValue("pago", "notFound", "not found");
			return "facturas/findFacturas";
		}else if(results.size()==1) {
			factura = results.iterator().next();
			return  "redirect:/factura/" +factura.getId();
		}else {
			model.put("selections", results);
			return "factura/ListaFacturas";
		}	
	}
}
