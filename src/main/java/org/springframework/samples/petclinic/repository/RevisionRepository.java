package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Revision;

public interface RevisionRepository {
	
	void save(Revision revision) throws DataAccessException;

	@Query("SELECT DISTINCT revision FROM Revision revision left join fetch revision.descripcion WHERE revision.fechaRevision:=fechaRevision")
	public Collection<Revision> findByFechaRevision(@Param("fechaRevision") LocalDate fechaRevision);

	void delete(Integer id) throws DataAccessException;
}
