package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Revision;

public interface RevisionRepository extends CrudRepository<Revision, Integer>{
	
	@Query("SELECT DISTINCT rev FROM Revision rev WHERE rev.fechaRevision = :fechaRevision")
	Collection<Revision> findByFechaRevision(@Param("fechaRevision") LocalDate fechaRevision);
	@Query("SELECT DISTINCT rev FROM Revision rev WHERE rev.asignada = :asignada")
	Collection<Revision> findByDisponible(@Param("asignada") Boolean asignada);
	Collection<Revision> findAll() throws DataAccessException;
	
}
