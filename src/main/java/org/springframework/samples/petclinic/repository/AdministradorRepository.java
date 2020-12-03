package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Administrador;

public interface AdministradorRepository extends Repository<Administrador, Integer> {

	public Administrador findAdministrador() throws DataAccessException;
	
	public void update(Administrador administrador) throws DataAccessException;

	public void save(Administrador administrador) throws DataAccessException;
}
