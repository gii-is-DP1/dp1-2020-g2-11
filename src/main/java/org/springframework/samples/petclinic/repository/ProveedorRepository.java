package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;

public interface ProveedorRepository extends CrudRepository<Proveedor, Integer> {
	
	@Query("SELECT proveedor FROM Proveedor proveedor WHERE proveedor.nombre =:nombre")
	public Collection<Proveedor> findByNombre(@Param("nombre") String nombre);
	
	@Query("SELECT p FROM Proveedor p WHERE p.disponible =:disponible")
	Collection<Proveedor> findDisponibles(@Param("disponible") boolean disponible);
	
	Collection<Proveedor> findAll() throws DataAccessException;
	
}
