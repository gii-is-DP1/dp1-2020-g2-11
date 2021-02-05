package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Reparacion;
import org.springframework.samples.petclinic.repository.ReparacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReparacionService {

	private ReparacionRepository reparacionRepository;

	@Autowired
	public ReparacionService(ReparacionRepository reparacionRepository) {
		this.reparacionRepository = reparacionRepository;
	}

	@Transactional
	public void saveReparacion(Reparacion reparacion) throws DataAccessException {
		reparacionRepository.save(reparacion);
	}

//	@Transactional
//	public void updateReparacion(Reparacion reparacion) throws DataAccessException {
//		reparacionRepository.update(reparacion);
//	}
	
	@Transactional
	public void deleteReparacion(Integer idReparacion) throws DataAccessException {
		reparacionRepository.deleteById(idReparacion);
	}
	
	@Transactional(readOnly = true)
	public Collection<Reparacion> findReparaciones() throws DataAccessException {
		return reparacionRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Reparacion> findReparacionById(Integer id) throws DataAccessException {
		return reparacionRepository.findById(id);
	}
}
