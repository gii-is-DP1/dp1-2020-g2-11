package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Revision;

public interface RevisionRepository extends CrudRepository<Revision, Integer>{
	
	@Query("SELECT DISTINCT rev FROM Revision rev WHERE rev.fechaRevision = :fechaRevision")
	Collection<Revision> findByFechaRevision(@Param("fechaRevision") LocalDate fechaRevision);
	@Query("SELECT DISTINCT rev FROM Revision rev WHERE rev.asignada = :asignada")
	Collection<Revision> findByDisponible(@Param("asignada") Boolean asignada);
	Collection<Revision> findAll() throws DataAccessException;
	@Query("SELECT rev FROM Revision rev, Mecanico mec WHERE rev.mecanico.id = :mecanico_id")
    public Set<Revision> findByMecanico(@Param("mecanico_id") Integer mecanicoId);
	
}
