package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
		return (Collection<Revision>) revisionRepository.findByDisponible(null);
	}
	@Transactional
	public void saveRevision(Revision revision) throws DataAccessException {
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



}