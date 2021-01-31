package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Productos;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoController {
	
	private ProductoService productoService;

	@Autowired
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	@GetMapping(value = { "/producto" })
	public String findAllProductos(ModelMap modelMap) {
//		Collection<Producto> productos = (Collection<Producto>) productoService.findProductos();
//		modelMap.addAttribute("selections", productos);
		Productos vets = new Productos();
		vets.getProductoList().addAll(this.productoService.findProductos());
		modelMap.put("selections", vets);
		return "producto/ListaProductos";
	}

	@GetMapping(value = { "/producto" })
	public String findProductosByNombre(Producto producto, BindingResult res, Map<String, Object> model) {
		if (producto.getNombre() == null) {
			producto.setNombre(""); // empty string signifies broadest possible search
		}
		// find Productos by name
		Collection<Producto> results = this.productoService.findProductoByNombre(producto.getNombre());
		if (results.isEmpty()) {
			// no Producto found
			res.rejectValue("nombre", "notFound", "not found");
			return "producto/findProductos";
		} else if (results.size() == 1) {
			// 1 Producto found
			producto = results.iterator().next();
			return "redirect:/producto/" + producto.getReferencia();
		} else {
			// multiple Productos found
			model.put("selections", results);
			return "producto/ListaProductos";
		}
	}

}
