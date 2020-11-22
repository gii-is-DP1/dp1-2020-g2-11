package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.Reparacion;
import org.springframework.samples.petclinic.repository.ReparacionRepository;
import org.springframework.samples.petclinic.repository.RevisionRepository;
import org.springframework.samples.petclinic.repository.VehiculoRepository;
import org.springframework.transaction.annotation.Transactional;

public class ReparacionService {
	
	private ReparacionRepository reparacionRepository;
	private VehiculoRepository vehiculoRepository;
	private RevisionRepository revisionRepository;
	
	@Autowired
	public ReparacionService(ReparacionRepository reparacionRepository, VehiculoRepository vehiculoRepository, RevisionRepository revisionRepository) {
		this.reparacionRepository = reparacionRepository;
		this.vehiculoRepository = vehiculoRepository;
		this.revisionRepository = revisionRepository;
	}
	
	@Transactional
	public void saveReparacion(Reparacion reparacion) throws DataAccessException {
		reparacionRepository.save(reparacion);
	}
	
	@Transactional
	public void updateReparacion(Reparacion reparacion) throws DataAccessException {
		reparacionRepository.update(reparacion);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Reparacion> findReparaciones() throws DataAccessException {
		return reparacionRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Collection<Reparacion> findReparacionById(Integer id) throws DataAccessException {
		return reparacionRepository.findByID(id);
	}

}
