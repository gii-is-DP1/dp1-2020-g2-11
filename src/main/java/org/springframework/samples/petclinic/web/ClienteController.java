package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.service.CitaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteController {

	private ClienteService clienteService;
	@Autowired
	private CitaService citaService;
	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping(value = { "/clientes" })
	public String findAllClientes(Map<String, Object> model) {
		Collection<Cliente> clientes = clienteService.findClientes();
		model.put("cliente", clientes);
		return "cliente/ListaClientes";
	}

	// crear nuevo cliente
	@GetMapping(value = "/clientes/new")
	public String initCreationForm(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		return "cliente/createOrUpdateClienteForm";
	}

	@PostMapping(value = "/clientes/new")
	public String processCreationForm(@Valid Cliente cliente, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("cliente", cliente);
			return "cliente/createOrUpdateClienteForm";
		} else {
			this.clienteService.saveCliente(cliente);
			return "redirect:/";
		}
	}

	@GetMapping(value = "/cliente/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("cliente", new Cliente());
		return "cliente/findClientes";
	}

	@GetMapping(value = { "/cliente" })
	public String processFindForm(Cliente cliente, BindingResult res, Map<String, Object> model) {
		if (cliente.getNombre() == null) {
			cliente.setNombre(""); // empty string signifies broadest possible search
		}
		// find clientes by name
		Collection<Cliente> results = this.clienteService.findClienteByNombre(cliente.getNombre());
		
		if (results.isEmpty()) {
			// no clientes found
			res.rejectValue("nombre", "notFound", "not found");
			return "redirect:/cliente/find";
			
		} else if (results.size() == 1) {
			// 1 cliente found
			cliente = results.iterator().next();
			return "redirect:/cliente/" + cliente.getId();
			
		} else {
			// multiple clientes found
			model.put("cliente", results);
			return "cliente/ListaClientes";
		}
	}

	@GetMapping(value = { "/cliente/{clienteId}" })
	public String findById(@PathVariable("clienteId") int clienteId, ModelMap map) {
		Cliente clientes = clienteService.findClienteById(clienteId).get();
		map.put("cliente", clientes);
		return "cliente/clienteDetails";
	}
	
//	@GetMapping(value = { "/cliente/delete/{clienteId}" })
//	public String deleteCliente(@PathVariable("clienteId") int clienteId) {
//		citaService.removeCitaByCliente(clienteId);
//		clienteService.deleteClienteById(clienteId);
//		return "redirect:/clientes";
//	}
}
