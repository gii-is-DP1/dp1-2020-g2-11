package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.FacturaService;
import org.springframework.samples.petclinic.service.exceptions.NoPagadaException;
import org.springframework.samples.petclinic.service.exceptions.TipoPagoException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FacturaController {
	
	private FacturaService facturaService;
	@Autowired
	private final ClienteService clienteService;
	
	@Autowired
	public FacturaController(FacturaService facturaService, ClienteService clienteService) {
		this.facturaService = facturaService;
		this.clienteService = clienteService;
	}

	@GetMapping(value = { "/facturas" })
	public String findAllFacturas(Map<String, Object> model) {
		Collection<Factura> facturas = facturaService.findFacturas();
		model.put("facturas", facturas);
		return "facturas/ListaFacturas";
	}
	
	@GetMapping(value = { "/facturas/cliente" })
	public String findMisFacturas(Map<String, Object> model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Cliente cliente = clienteService.findClienteByUsername(clienteDetails.getUsername());
		Integer clienteId = cliente.getId();
		Collection<Factura> facturas = facturaService.findMisfacturas(clienteId);
		model.put("facturas", facturas);
		return "facturas/ListaFacturas";
	}
	@GetMapping(value = "/factura/Pagada/{facturaId}")
	public String facturaPagada(@PathVariable("facturaId") int facturaId) {
		facturaService.setFacturaPagado(facturaId);
		return"redirect:/facturas";
	}
	
	@GetMapping(value = "/factura/new")
	public String initCreationForm(ModelMap model) {
		Factura factura = new Factura();
		String dni = "";
		model.put("factura", factura);
		model.put("dni", dni);
		return "facturas/FormularioFactura";
	}
	@PostMapping(value = "/factura/new")
	public String processCreationForm(@Valid Factura factura, BindingResult result,ModelMap model) throws DataAccessException, TipoPagoException {
		if (result.hasErrors()) {
			model.put("factura", factura);
			return "facturas/FormularioFactura";
		}
		else {
			Cliente c = this.clienteService.findClienteByDni(factura.getCliente().getDni());
			factura.setCliente(c);
			this.facturaService.saveFactura(factura);
			return "redirect:/facturas";
		}
	}

	@GetMapping(value = { "/facturaId" })
	public String findFacturaById(Factura factura, BindingResult res, ModelMap model) {
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
			model.put("facturaID", results);
			return "facturas/ListaFacturas";
		}
	}

	@GetMapping(value = { "/facturaFechaEmision" })
	public String findFacturaByFechaEmision(Factura factura, BindingResult res, ModelMap model, LocalDate fecha) {
		if (factura.getFechaEmision() == null) {
			Collection<Factura> results = this.facturaService.findFacturas();
			model.addAttribute("facturaFecha", results);
			return "facturas/ListaFacturas";
		} else {
			Collection<Factura> results = this.facturaService.findFacturabyFechaEmision(fecha);

			if (results.isEmpty()) {
				res.rejectValue("fecha", "notFound", "not found");
				return "facturas/ListaFacturas";
			}else if(results.size()==1) {
				factura= results.iterator().next();
				return  "redirect:/factura/" +factura.getId();
			} else {
				model.addAttribute("facturaFecha", results);
				return "facturas/ListaFacturas";
			}
		}
	}

	@GetMapping(value = { "/facturaPago" })
	public String findFacturaPagado(Factura factura, Boolean pago, BindingResult res, ModelMap model) {
		if (factura.getPagado() == null) {
			Collection<Factura> results = this.facturaService.findFacturas();
			model.addAttribute("facturaPagado", results);
			return "facturas/ListaFacturas";
		}else {
			Collection<Factura> results = this.facturaService.findFacturaPagado(pago);
			if (results.isEmpty()) {
				res.rejectValue("pago", "notFound", "not found");
				return "facturas/findFacturas";
			}else if(results.size()==1) {
				factura = results.iterator().next();
				return  "redirect:/factura/" +factura.getId();
			} else {
				model.addAttribute("facturaPagado", results);
				return "facturas/ListaFacturas";
			}
		}
		
		
		
	}
}
