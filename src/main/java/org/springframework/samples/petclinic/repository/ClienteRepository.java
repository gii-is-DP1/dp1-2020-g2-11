package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
	
	@Query("SELECT DISTINCT c FROM Cliente c WHERE c.dni = :dni")
	public Cliente findByDni(@Param("dni") String dni);        

	public Cliente findById(@Param("id") int id);	

	@Query("SELECT c FROM Cliente c WHERE c.nombre = :nombre")
	public Collection<Cliente> findByNombre(@Param("nombre") String nombre);
	
	@Query("SELECT c FROM Cliente c WHERE c.user.username = :username")
	public Cliente findByUsername(@Param("username") String username);
	
}