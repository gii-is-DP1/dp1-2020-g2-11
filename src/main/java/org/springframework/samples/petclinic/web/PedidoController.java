package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PedidoController {
	
	private ProveedorService proveedorService;
	
	@Autowired
	public PedidoController(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}
	
	@GetMapping(value= {"/pedido"})
	public String findAllPedidos(Map<String, Object> model) {
		Collection<Pedido> pedidos = proveedorService.findPedidos();
		model.put("selections", pedidos);
		return "pedido/ListaPedidos";
	}

}
