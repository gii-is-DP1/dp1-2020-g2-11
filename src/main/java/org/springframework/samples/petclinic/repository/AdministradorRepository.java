package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Administrador;

public interface AdministradorRepository extends CrudRepository<Administrador, Integer> {

	//void update(Integer id) throws DataAccessException;
}
