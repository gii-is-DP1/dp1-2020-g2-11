package org.springframework.samples.petclinic.web;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

	private static final String VIEWS_CLIENTE_CREATE_FORM = "users/createClienteForm";

	private final ClienteService clienteService;

	@Autowired
	public UserController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	@InitBinder("user")
    public void initRevisionBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new UserValidator());
    }
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		return VIEWS_CLIENTE_CREATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_CLIENTE_CREATE_FORM;
		}
		else {
			this.clienteService.saveCliente(cliente);
			return "redirect:/";
		}
	}
}