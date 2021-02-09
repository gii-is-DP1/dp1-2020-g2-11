package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Mecanico;
import org.springframework.samples.petclinic.model.Revision;
import org.springframework.samples.petclinic.repository.RevisionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RevisionService {
	
	private RevisionRepository revisionRepository;
	@Autowired
	public RevisionService(RevisionRepository revisionRepository) {
		this.revisionRepository = revisionRepository;
	}
	
	@Transactional(readOnly = true)
	public Collection<Revision> findAllRevisiones() throws DataAccessException {
		return (Collection<Revision>) revisionRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Collection<Revision> findAllRevisionesDisponibles() throws DataAccessException {
		return (Collection<Revision>) revisionRepository.findByDisponible(false);
	}
	@Transactional
	public void saveRevision(Revision revision) throws DataAccessException {
		revision.setAsignada(false);
		revision.setMecanico(null);
		revisionRepository.save(revision);
	}

	@Transactional
	public void deleteRevision(Integer idRevision) throws DataAccessException {
		revisionRepository.deleteById(idRevision);
	}
	
	@Transactional(readOnly = true)
	public Collection<Revision> findRevisionByFecha(LocalDate fechaRevision) throws DataAccessException {
		return revisionRepository.findByFechaRevision(fechaRevision);
	}
	@Transactional(readOnly = true)
	public Optional<Revision> findRevisionById(Integer id) throws DataAccessException {
		return revisionRepository.findById(id);
	}
	@Transactional
	public void AsignedRevision(Integer id,Mecanico mecanico) {
		Revision revision=revisionRepository.findById(id).get();
		revision.setAsignada(true);
		revision.setMecanico(mecanico);
	
	}

	public Set<Revision> findMisRevisiones(Integer mecanicoId) {
		return (Set<Revision>) revisionRepository.findByMecanico(mecanicoId);
	}



}