package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PedidoController {
	
	private PedidoService pedidoService;
	
	@Autowired
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@GetMapping(value= {"/pedidos"})
	public String findAllPedidos(Map<String, Object> model) {
		Collection<Pedido> pedidos = pedidoService.findAllPedidos();
		model.put("pedidos", pedidos);
		return "pedidos/ListaPedidos";
	}

}
