package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Revision;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.RevisionService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RevisionController {

	private RevisionService revisionService;
	@Autowired
	private final ClienteService clienteService;
	@Autowired
	private final VehiculoService vehiculoService;
	@Autowired
	public RevisionController(RevisionService revisionService, ClienteService clienteService, VehiculoService vehiculoService) {
		this.revisionService = revisionService;
		this.clienteService = clienteService;
		this.vehiculoService = vehiculoService;
	}

	@GetMapping(value = { "/revisiones" })
	public String findAllRevision(ModelMap modelMap) {
		Collection<Revision> revisiones = (Collection<Revision>) revisionService.findAllRevisiones();
		modelMap.addAttribute("revisiones", revisiones);
		return "revisiones/ListaRevisiones";
	}

	@GetMapping(value = { "/revisionFecha" })
	public String findRevisionByFecha(LocalDate fecha, Revision revision, BindingResult res, ModelMap modelMap) {
		if (revision.getFechaRevision() == null) {
			Collection<Revision> results = this.revisionService.findAllRevisiones();
			modelMap.addAttribute("revisiones",results);
			return "revisiones/ListaRevisiones";
		} else {
			Collection<Revision> results = this.revisionService.findRevisionByFecha(fecha);
			if (results.isEmpty()) {
				res.rejectValue("fecha", "notFound", "not found");
				return "revisiones/ListaRevisiones";
			} else if (results.size() == 1) {
				revision = results.iterator().next();
				return "redirect:/revisiones/" + revision.getId();
			} else {
				modelMap.addAttribute("revisiones", results);
				return "revisiones/ListaRevisiones";
			}
		}
	}
	@GetMapping(value = { "/revision/{revisionId}" })
	public String findById(@PathVariable("revisionId") int revisionId, ModelMap map) {
		Revision revision = revisionService.findRevisionById(revisionId).get();
		map.put("revision", revision);
		return "revisiones/revisionDetails";
	}




	@GetMapping(value = { "/revision/delete/{revisionId}" })
	public String deleteRevision(@PathVariable("revisionId") int revisionId) {
		revisionService.deleteRevision(revisionId);
		return "redirect:/revisiones";
	}
	
	@GetMapping(value = "/revision/new")
	public String initCreationForm(ModelMap model) {
		Revision revision = new Revision();
		String dni = "";
		String matricula = "";
		model.put("revision", revision);
		model.put("dni", dni);
		model.put("matricula", matricula);
		return "revisiones/FormularioRevision";
	}
	@PostMapping(value = "/revision/new")
	public String processCreationForm(@Valid Revision revision, BindingResult result,ModelMap model) throws DataAccessException {
		if (result.hasErrors()) {
			model.put("revision", revision);
			return "revisiones/FormularioRevision";
		}
		else {
			Cliente c = this.clienteService.findClienteByDni(revision.getCliente().getDni());
			Vehiculo v = this.vehiculoService.findVehiculoByMatricula(revision.getVehiculo().getMatricula());
			revision.setCliente(c);
			revision.setVehiculo(v);
			this.revisionService.saveRevision(revision);
			return "redirect:/revisiones";
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

