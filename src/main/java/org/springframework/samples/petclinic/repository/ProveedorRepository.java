package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Proveedor;

public interface ProveedorRepository extends Repository<Proveedor, Integer> {
	
	void save (Proveedor proveedor) throws DataAccessException;
	
	@Query("SELECT proveedor FROM Proveedor proveedor WHERE proveedor.nombre =:nombre")
	public Collection<Proveedor> findByNombre(@Param("nombre") String nombre);
	
	Collection<Proveedor> findAll() throws DataAccessException;
	
	//void delete(String nombre) throws DataAccessException;
	

}
