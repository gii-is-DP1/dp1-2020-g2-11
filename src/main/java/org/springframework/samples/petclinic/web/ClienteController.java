package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController {

	private ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping(value = { "/clientes" })
	public String findAllClientes(Map<String, Object> model) {
		Collection<Cliente> clientes = clienteService.findClientes();
		model.put("selections", clientes);
		return "cliente/findClientes";
	}
	
	//crear nuevo cliente
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
		}
		else {
			this.clienteService.saveCliente(cliente);
			return "redirect:/";
		}
	}
	
	
	@GetMapping(value = "/cliente/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("cliente", new Cliente());
		return "cliente/ListaClientes";
	}

	@GetMapping(value = { "/cliente" })
	public String findClientesByNombre(Cliente cliente, BindingResult res, Map<String, Object> model) {
		if (cliente.getNombre() == null) {
			cliente.setNombre(""); // empty string signifies broadest possible search
		}
		// find clientes by name
		Collection<Cliente> results = this.clienteService.findClienteByNombre(cliente.getNombre());
		if (results.isEmpty()) {
			// no clientes found
			res.rejectValue("nombre", "notFound", "not found");
			return "cliente/findClientes";
		} else if (results.size() == 1) {
			// 1 cliente found
			cliente = results.iterator().next();
			return "redirect:/cliente/" + cliente.getId();
		} else {
			// multiple clientes found
			model.put("selections", results);
			return "cliente/ListaCliente";
		}
	}
}
