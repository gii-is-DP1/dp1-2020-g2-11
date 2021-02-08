package org.springframework.samples.petclinic.web;
import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PedidoController {
	
	private PedidoService pedidoService;
	
	@Autowired
	private final ProveedorService proveedorService;
	@Autowired
	private final ProductoService productoService;

	
	@Autowired
	public PedidoController(PedidoService pedidoService, ProveedorService proveedorService, ProductoService productoService) {
		this.pedidoService = pedidoService;
		this.proveedorService=proveedorService;
		this.productoService=productoService;
	}
	
	@GetMapping(value= {"/pedidos"})
	public String findAllPedidos(Map<String, Object> model) {
		Collection<Pedido> pedidos = pedidoService.findAllPedidos();
		model.put("pedidos", pedidos);
		return "pedidos/ListaPedidos";
	}
	
	@GetMapping("/pedido/{pedidoId}/edit")
	public String initUpdateOwnerForm(@PathVariable("pedidoId") int pedidoId, ModelMap model, Model mo ) {
		Pedido pedido = this.pedidoService.findById(pedidoId).get();
		Collection<Proveedor> proveedores=proveedorService.findProveedoresDisponibles();
		model.put("proveedor", proveedores);
		Collection <Producto> productos=productoService.findProductosDisponibles();
		model.put("producto", productos);		
		mo.addAttribute(pedido);
		return "pedidos/FormularioPedido";
	}

	@PostMapping("/pedido/{pedidoId}/edit")
	public String processUpdateOwnerForm(@Valid Pedido pedido, BindingResult result,
			@PathVariable("pedidoId") int pedidoId) {
		if (result.hasErrors()) {
			return "pedidos/FormularioPedido";
		}
		else {
			pedido.setId(pedidoId);
			Proveedor prov= this.proveedorService.findProveedorById(pedido.getProveedor().getId());
			pedido.setProveedor(prov);
			Producto pro=this.productoService.findProductoByReferencia(pedido.getProducto().getReferencia());
			pedido.setProducto(pro);
			this.pedidoService.savePedido(pedido);
			return "redirect:/pedidos";
		}
	}
	
	@GetMapping(value = "/pedido/new")
	public String initCreationForm(ModelMap model) {
		Pedido pedido = new Pedido();
		Collection<Proveedor> proveedores=proveedorService.findProveedoresDisponibles();
		model.put("proveedor", proveedores);
		Collection <Producto> productos=productoService.findProductosDisponibles();
		model.put("producto", productos);
		String referencia = "";
		model.put("pedido", pedido);
		model.put("referencia", referencia);
		return "pedidos/FormularioPedido";
	}
	
	@PostMapping(value = "/pedido/new")
	public String processCreationForm(@Valid Pedido pedido, BindingResult result,ModelMap model) throws DataAccessException{
		if (result.hasErrors()) {
			model.put("pedido", pedido);
			return "pedidos/FormularioPedido";
		}
		else {
			Proveedor pr= this.proveedorService.findProveedorById(pedido.getProveedor().getId());
			Producto pro=this.productoService.findProductoByReferencia(pedido.getProducto().getReferencia());
			pedido.setProveedor(pr);
			pedido.setProducto(pro);
			this.pedidoService.savePedido(pedido);
			return "redirect:/pedidos";
		}
	}
	
	@PostMapping(value = { "/pedidos" })
	public String findPedidosByFechaEmision(@Valid Pedido pedido, BindingResult res, Map<String, Object> model) {
		// buscamos pedidos por fecha
		Collection<Pedido> pedidos = this.pedidoService.findPedidoByfechaDeEmision(pedido.getFechaEmision());
		model.put("pedidos", pedidos);
		return "pedidos/ListaPedidos";
	}
}
