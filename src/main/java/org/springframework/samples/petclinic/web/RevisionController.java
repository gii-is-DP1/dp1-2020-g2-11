package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Reparacion;
import org.springframework.samples.petclinic.model.Revision;
import org.springframework.samples.petclinic.service.ReparacionService;
import org.springframework.samples.petclinic.service.RevisionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RevisionController {
	
	private RevisionService revisionService;
	
	@Autowired
	public RevisionController(RevisionService revisionService) {
		this.revisionService=revisionService;
	}
	@GetMapping(value = { "/revisiones" })
	public String findAllRevision(Map<String, Object> model) {
		Collection<Revision> reparaciones = revisionService.findAllRevisiones();
		model.put("selections", reparaciones);
		return "revisiones/ListaRevisiones";
	}
	
	@GetMapping(value = { "/revision" })
	public String findRevisionByFecha(Revision revision, BindingResult res, Map<String, Object> model) {
		if (revision.getFechaRevision() == null) {
			revision.setFechaRevision(LocalDate.parse("")); // empty string signifies broadest possible search
		}
		Collection<Revision> results= this.revisionService.findRevisionByFecha(revision.getFechaRevision());
		if (results.isEmpty()) {
			res.rejectValue("fecha", "notFound", "not found");
			return "revisiones/findRevisones";
		}else if(results.size()==1) {
			revision= results.iterator().next();
			return  "redirect:/revision/" +revision.getId();
		}else {
			model.put("selections", results);
			return "revision/ListaRevision";
		}
		
	}
}