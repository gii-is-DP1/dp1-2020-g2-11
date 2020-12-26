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

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Estancia;

/**
 * Spring Data JPA OwnerRepository interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface EstanciaRepository extends CrudRepository<Estancia, Integer> {

	@Query("SELECT DISTINCT e FROM Estancia e WHERE e.fechaEntrada = :fechaEntrada")
	Collection<Estancia> findByFechaEstancia(@Param("fechaEntrada") LocalDateTime fechaEntrada);
	
	@Query("SELECT DISTINCT e FROM Estancia e WHERE e.duracion = :null")
	Collection<Estancia> findByEstacionados();

	Collection<Estancia> findAll() throws DataAccessException;

}
