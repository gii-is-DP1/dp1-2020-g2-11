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

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Estancia;

/**
 * Spring Data JPA OwnerRepository interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface EstanciaRepository extends Repository<Estancia, Integer> {

	void save(Estancia estancia) throws DataAccessException;

	@Query("SELECT DISTINCT cita FROM Estancia estancia left join fetch estancia.fechaEntrada, estancia.fechaSalida, estancia.duracion WHERE estancia.fechaEntrada LIKE :fechaEntrada%")
	public Collection<Estancia> findByFechaEstancia(@Param("fechaEntrada") LocalDate fechaEntrada);

	@Query("SELECT estancia FROM Estancia estancia left join fetch estancia.fechaEntrada, estancia.fechaSalida, estancia.duracion WHERE estancia.id =:id")
	public Estancia findById(@Param("id") int id);
	
	Collection<Estancia> findAll() throws DataAccessException;

}
