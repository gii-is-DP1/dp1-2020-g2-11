package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer>{
		
	@Query("SELECT DISTINCT p FROM Producto p WHERE p.nombre = :nombre")
	Collection<Producto> findByNombre(@Param("nombre") String nombre);
	
	@Query("SELECT p FROM Producto p WHERE p.marca =:marca")
	Collection<Producto> findByMarca(@Param("marca") String marca);
	
	@Query("SELECT p FROM Producto p WHERE p.referencia =:referencia")
	Producto findByReferencia(@Param("referencia") String referencia);

}
