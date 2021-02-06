package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoController {

	private ProductoService productoService;

	@Autowired
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	@GetMapping(value = { "/productos" })
	public String findAllProductos(Map<String, Object> model) {
		Collection<Producto> productos = (Collection<Producto>) productoService.findProductosDisponibles();
		model.put("producto", productos);
		return "productos/ListaProductos";
	}

	@PostMapping(value = { "/productos" })
	public String findProductosByNombre(String nombre, @Valid Producto producto, BindingResult res,
			Map<String, Object> model) {
		Collection<Producto> productos = this.productoService.findProductoByNombre(nombre);
		model.put("producto", productos);
		return "productos/ListaProductos";
	}
	

	@GetMapping(value = "/productos/new")
	public String initCreationForm(ModelMap model) {
		Producto producto = new Producto();
		
		model.put("producto", producto);
		producto.setDisponible(true);
		return "productos/FormularioProducto";
	}

	@PostMapping(value = "/productos/new")
	public String processCreationForm(@Valid Producto producto, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.put("producto", producto);
			producto.setDisponible(true);
			return "productos/FormularioProducto";
		} else {
			
			model.put("producto", producto);
			producto.setDisponible(true);
			this.productoService.saveProducto(producto);
			
			return "redirect:/productos";
		}
	}
	
	@GetMapping(value = { "/productos/oculta/{productoId}" })
	public String ocultaProducto(@PathVariable("productoId") Integer productoId) {
		productoService.ocultarProducto(productoId);
		return "redirect:/productos";
	}
	
	

}
