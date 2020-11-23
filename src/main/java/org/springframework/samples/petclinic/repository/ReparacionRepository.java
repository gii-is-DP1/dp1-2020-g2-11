package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Reparacion;

public interface ReparacionRepository {

	void save(Reparacion reparacion) throws DataAccessException;

	@Query("SELECT DISTINCT reparacion FROM Reparacion reparacion left join fetch reparacion.precio, reparacion.tipoReparacion WHERE reparacion.id=:id")
	public Collection<Reparacion> findByID(@Param("id") Integer id);

	Collection<Reparacion> findAll() throws DataAccessException;

	void update(Reparacion reparacion) throws DataAccessException;
}