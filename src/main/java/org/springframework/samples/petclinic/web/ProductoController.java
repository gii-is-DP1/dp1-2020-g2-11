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
		Collection<Producto> productos = (Collection<Producto>) productoService.findProductos();
		model.put("producto", productos);
		return "productos/ListaProductos";
	}

	@GetMapping(value = { "/productoNombre" })
	public String findProductosByNombre(String nombre, Producto producto, BindingResult res,
			Map<String, Object> model) {
		if (producto.getNombre() == null) {
			producto.setNombre(""); // empty string signifies broadest possible search
		}
		// find Productos by name
		Collection<Producto> results = this.productoService.findProductoByNombre(nombre);
		if (results.isEmpty()) {
			// no Producto found
			res.rejectValue("nombre", "notFound", "not found");
			return "productos/ListaProductos";
		} else if (results.size() == 1) {
			// 1 Producto found
			producto = results.iterator().next();
			return "redirect:/productos/" + producto.getId();
		} else {
			// multiple Productos found
			model.put("selections", results);
			return "productos/ListaProductos";
		}
	}

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
			return "redirect:/productos/ListaProductos";
		}
	}

}
