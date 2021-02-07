package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoController {

	private ProductoService productoService;

	@Autowired
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}
	
	@InitBinder("producto")
    public void initProductoBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new ProductoValidator());
    }

	@GetMapping(value = { "/productos" })
	public String findAllProductos(Map<String, Object> model) {
		Collection<Producto> productos = (Collection<Producto>) productoService.findProductosDisponibles();
		model.put("producto", productos);
		return "productos/ListaProductos";
	}
	
	@GetMapping(value = { "/productos/productosNoDisonibles" })
	public String findAllProductosNoDisponibles(Map<String, Object> model) {
		Collection<Producto> productos = (Collection<Producto>) productoService.findProductosNoDisponibles();
		model.put("producto", productos);
		return "productos/ProductosNoDisponibles";
	}

//	@PostMapping(value = { "/productos" })
//	public String findProductosByNombre(String nombre, @Valid Producto producto, BindingResult res,
//			Map<String, Object> model) {
//		Collection<Producto> productos = this.productoService.findProductoByNombre(nombre);
//		model.put("producto", productos);
//		return "productos/ListaProductos";
//	}
	

	@GetMapping(value = "/productos/new")
	public String initCreationForm(ModelMap model) {
		Producto producto = new Producto();
		
		model.put("producto", producto);
		
		return "productos/FormularioProducto";
	}

	@PostMapping(value = "/productos/new")
	public String processCreationForm(@Valid Producto producto, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			
			model.put("producto", producto);
			return "productos/FormularioProducto";
		} else {
			
			model.put("producto", producto);
			this.productoService.saveProducto(producto);
			return "redirect:/productos";
		}
	}
	
	
	
	@GetMapping(value = { "/productos/oculta/{productoId}" })
	public String ocultaProducto(@PathVariable("productoId") Integer productoId) {
		productoService.ocultarProducto(productoId);
		return "redirect:/productos";
	}
	
	@GetMapping(value = { "/productos/devuelve/{productoId}" })
	public String develveProducto(@PathVariable("productoId") Integer productoId) {
		productoService.devuelveProducto(productoId);
		return "redirect:/productos";
	}
	
	@GetMapping("/productos/{productoId}/edit")
	public String initUpdateProductoForm(@PathVariable("productoId") int productoId, Model model) {
		Producto producto = this.productoService.findProductoById(productoId);
		model.addAttribute(producto);
		return "productos/FormularioProducto";
	}

	@PostMapping("/productos/{productoId}/edit")
	public String processUpdateProductoForm(@Valid Producto producto, BindingResult result,  ModelMap model,
			@PathVariable("productoId") int productoId) {
		if (result.hasErrors()) {
			model.put("producto", producto);
			return "productos/FormularioProducto";
		}
		else {
			producto.setId(productoId);
			this.productoService.saveProducto(producto);
			return "redirect:/productos";
		}
	}
	
	

}
