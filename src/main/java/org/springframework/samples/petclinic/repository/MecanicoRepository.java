

package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Mecanico;

public interface MecanicoRepository extends Repository<Mecanico, Integer>{
	
	@Query("SELECT mecanico FROM Mecanico mecanico WHERE mecanico.id =:id")
    public Mecanico findById(@Param("id") int id);

	Collection<Mecanico> findAll() throws DataAccessException;
	
	void save(Mecanico mecanico) throws DataAccessException;
	
}
