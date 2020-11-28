package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Producto;

public interface ProductoRepository extends Repository<Producto, Integer>{
	
	
	@Query("SELECT producto FROM Producto producto left join fetch producto.nombre, producto.marca, producto.stock WHERE producto.nombre =:nombre")
	public Collection<Producto> findByNombre(@Param("producto") String nombre);
	
	@Query("SELECT producto FROM Producto producto left join fetch producto.nombre, producto.marca, producto.stock WHERE producto.marca =:marca")
	public Collection<Producto> findByMarca(@Param("marca") String marca);
	
	@Query("SELECT producto FROM Producto producto left join fetch producto.nombre, producto.marca, producto.stock WHERE producto.referencia =:referencia")
	public Collection<Producto> findByReferencia(@Param("referencia") String referencia);

	void save(Producto producto) throws DataAccessException;
	
	Collection<Producto> findAll() throws DataAccessException;

	void delete(String referencia) throws DataAccessException;
	
	void update(Producto producto) throws DataAccessException;
}
