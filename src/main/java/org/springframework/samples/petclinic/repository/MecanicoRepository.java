

package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Mecanico;


 
public interface MecanicoRepository extends Repository<Mecanico, Integer>{

	
	Collection<Mecanico> findAll() throws DataAccessException;

}
