package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Mecanico;
import org.springframework.samples.petclinic.model.Revision;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.MecanicoService;
import org.springframework.samples.petclinic.service.RevisionService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	private final MecanicoService mecanicoService;
	@Autowired
	public RevisionController(RevisionService revisionService, ClienteService clienteService,
			VehiculoService vehiculoService,MecanicoService mecanicoService) {
		this.revisionService = revisionService;
		this.clienteService = clienteService;
		this.vehiculoService = vehiculoService;
		this.mecanicoService = mecanicoService;
	}

	@InitBinder("revision")
    public void initRevisionBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new RevisionValidator());
    }
	
	@GetMapping(value = { "/revisiones" })
	public String findAllRevision(ModelMap modelMap) {
		Collection<Revision> revisiones = (Collection<Revision>) revisionService.findAllRevisiones();
		modelMap.addAttribute("revisiones", revisiones);
		return "revisiones/ListaRevisiones";
	}
	@GetMapping(value = { "/revisionesNoAsignadas" })
	public String findAllRevisionDisponible(ModelMap modelMap) {
		Collection<Revision> revisiones = (Collection<Revision>) revisionService.findAllRevisionesDisponibles();
		modelMap.addAttribute("revisiones", revisiones);
		return "revisiones/revisionesMecanico";
	}

	@GetMapping(value = { "/revisionFecha" })
	public String findRevisionByFecha(LocalDate fecha, Revision revision, BindingResult res, ModelMap modelMap) {
		if (revision.getFechaRevision() == null) {
			Collection<Revision> results = this.revisionService.findAllRevisiones();
			modelMap.addAttribute("revisiones", results);
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
	
	@GetMapping(value = "/revision/new")
	public String initCreationForm(ModelMap model) {
		Revision revision = new Revision();
		Collection<Cliente> clienteRegistered = this.clienteService.findClientes();
		Collection<Vehiculo> vehiculo = vehiculoService.findVehiculos();
		model.put("vehiculo", vehiculo);
		model.put("cliente", clienteRegistered);
		String dni = "";
		String matricula = "";
		model.put("revision", revision);
		model.put("dni", dni);
		model.put("matricula", matricula);
		return "revisiones/FormularioRevision";
	}

	@PostMapping(value = "/revision/new")
	public String processCreationForm(@Valid Revision revision, BindingResult result, ModelMap model)
			throws DataAccessException {
		Collection<Cliente> clienteRegistered = this.clienteService.findClientes();
		Collection<Vehiculo> vehiculo = vehiculoService.findVehiculos();
		model.put("vehiculo", vehiculo);
		model.put("cliente", clienteRegistered);
		if (result.hasErrors()) {
			model.put("revision", revision);
			return "revisiones/FormularioRevision";
		} else {
			Cliente c = this.clienteService.findClienteByDni(revision.getCliente().getDni());
			Vehiculo v = this.vehiculoService.findVehiculoByMatricula(revision.getVehiculo().getMatricula());
			revision.setCliente(c);
			revision.setVehiculo(v);
			this.revisionService.saveRevision(revision);
			return "redirect:/revisionesNoAsignadas";
		}
	}

	@GetMapping(value = "/revision/asignar/{revisionId}")
	public String setRevision(@PathVariable("revisionId") int revisionId) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = clienteDetails.getUsername();
		Mecanico mecanico =this.mecanicoService.findByUsername(username);
		revisionService.AsignedRevision(revisionId, mecanico);
		return "redirect:/revisiones/mecanico";
	}

	@GetMapping(value = { "/revision/{revisionId}" })
	public String findById(ModelMap model,@PathVariable("revisionId") int revisionId) {
		Revision revision = revisionService.findRevisionById(revisionId).get();
		model.put("revision", revision);
		return "revisiones/revisionDetails";
	}

	@GetMapping(value = { "/revision/delete/{revisionId}" })
	public String deleteRevision(@PathVariable("revisionId") int revisionId) {
		revisionService.deleteRevision(revisionId);
		return "redirect:/revisionesNoAsignadas";

	}
	
	@GetMapping(value = { "/revisiones/mecanico" })
	public String findMisRevisiones(Map<String, Object> model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Mecanico mecanico= mecanicoService.findByUsername(clienteDetails.getUsername());
		Integer mecanicoId = mecanico.getId();
		Collection<Revision> revisiones = revisionService.findMisRevisiones(mecanicoId);
		model.put("revisiones", revisiones);
		return "revisiones/ListaRevisiones";
	}
  }

