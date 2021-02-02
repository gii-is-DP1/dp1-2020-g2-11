package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Revision;
import org.springframework.samples.petclinic.service.RevisionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RevisionController {

	private RevisionService revisionService;

	@Autowired
	public RevisionController(RevisionService revisionService) {
		this.revisionService = revisionService;
	}

	@GetMapping(value = { "/revisiones" })
	public String findAllRevision(ModelMap modelMap) {
		Collection<Revision> revisiones = (Collection<Revision>) revisionService.findAllRevisiones();
		modelMap.addAttribute("revisiones", revisiones);
		return "revisiones/Revisiones";
	}

	@GetMapping(value = { "/revisionFecha" })
	public String findRevisionByFecha(LocalDate fecha, Revision revision, BindingResult res, ModelMap modelMap) {
		if (revision.getFechaRevision() == null) {
			Collection<Revision> results = this.revisionService.findAllRevisiones();
			modelMap.addAttribute("revisiones",results);
			return "revisiones/Revisiones";
		} else {
			Collection<Revision> results = this.revisionService.findRevisionByFecha(fecha);
			if (results.isEmpty()) {
				res.rejectValue("fecha", "notFound", "not found");
				return "revisiones/Revisiones";
			} else if (results.size() == 1) {
				revision = results.iterator().next();
				return "redirect:/revisiones/" + revision.getId();
			} else {
				modelMap.addAttribute("revisiones", results);
				return "revisiones/Revisiones";
			}
		}
	}

//	@GetMapping(value = { "/productonombre" })
//	public String findProductosByNombre(String nombre, Producto producto, BindingResult res, Map<String, Object> model) {
//		if (producto.getNombre() == null) {
//			producto.setNombre(""); // empty string signifies broadest possible search
//		}
//		// find Productos by name
//		Collection<Producto> results = this.productoService.findProductoByNombre(nombre);
//		if (results.isEmpty()) {
//			// no Producto found
//			res.rejectValue("nombre", "notFound", "not found");
//			return "producto/ListaProductos";
//		} else if (results.size() == 1) {
//			// 1 Producto found
//			producto = results.iterator().next();
//			return "redirect:/producto/" + producto.getId();
//		} else {
//			// multiple Productos found
//			model.put("selections", results);
//			return "producto/ListaProductos";
//		}
//	}

}