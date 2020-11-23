package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Reparacion;
import org.springframework.samples.petclinic.model.Revision;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.repository.ReparacionRepository;
import org.springframework.samples.petclinic.repository.RevisionRepository;
import org.springframework.samples.petclinic.repository.VehiculoRepository;
import org.springframework.transaction.annotation.Transactional;

public class ReparacionService {

	private ReparacionRepository reparacionRepository;
	private VehiculoRepository vehiculoRepository;
	private RevisionRepository revisionRepository;

	@Autowired
	public ReparacionService(ReparacionRepository reparacionRepository, VehiculoRepository vehiculoRepository,
			RevisionRepository revisionRepository) {
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

	@Transactional(readOnly = true)
	public Collection<Vehiculo> findVehículoByMatricula(String matricula) throws DataAccessException {
		return vehiculoRepository.findByMatricula(matricula);
	}

	@Transactional(readOnly = true)
	public Collection<Vehiculo> findVehículoByTipo(TipoVehiculo tipo) throws DataAccessException {
		return vehiculoRepository.findByTipoVehiculo(tipo);
	}

	@Transactional(readOnly = true)
	public Collection<Vehiculo> findVehiculos() throws DataAccessException {
		return vehiculoRepository.findAll();
	}

	@Transactional
	public void saveVehiculo(Vehiculo vehiculo) throws DataAccessException {
		vehiculoRepository.save(vehiculo);
	}

	@Transactional
	public void deleteVehiculo(Vehiculo vehiculo) throws DataAccessException {
		vehiculoRepository.delete(vehiculo);
	}

	@Transactional(readOnly = true)
	public Collection<Revision> findRevisionByFecha(LocalDate fechaRevision) throws DataAccessException {
		return revisionRepository.findByFechaRevision(fechaRevision);
	}

	@Transactional
	public void saveRevision(Revision revision) throws DataAccessException {
		revisionRepository.save(revision);
	}

	@Transactional
	public void deleteRevision(Integer idRevision) throws DataAccessException {
		revisionRepository.delete(idRevision);
	}
}