/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cliente;

public interface ClienteRepository extends Repository<Cliente, Integer> {
	
	@Query("SELECT DISTINCT cliente FROM Cliente cliente left join fetch cliente.nombre, cliente.apellidos, cliente.dni, cliente.telefono, cliente.email WHERE cliente.dni LIKE :dni%")
	public Collection<Cliente> findByDni(@Param("dni") String dni);        

	@Query("SELECT DISTINCT cliente FROM Cliente cliente left join fetch cliente.nombre, cliente.apellidos, cliente.dni, cliente.telefono, cliente.email WHERE cliente.id =:id")
	public Cliente findById(@Param("id") int id);
	
	@Query("SELECT DISTINCT cliente FROM Cliente cliente left join fetch cliente.nombre, cliente.apellidos, cliente.dni, cliente.telefono, cliente.email WHERE cliente.nombre =:nombre")
	public Cliente findByNombre(@Param("nombre") String nombre);
	
//	@Query("SELECT DISTINCT cliente FROM Cliente cliente left join fetch cliente.nombre, cliente.apellidos, cliente.dni, cliente.telefono, cliente.email, cliente.factura WHERE cliente.id =:id")
//    public Cliente findByIdfactura(@Param("id") int id);
	
    void save(Cliente cliente) throws DataAccessException; 
	
	Collection<Cliente> findAll() throws DataAccessException;

	void delete(Cliente cliente) throws DataAccessException;
		
}