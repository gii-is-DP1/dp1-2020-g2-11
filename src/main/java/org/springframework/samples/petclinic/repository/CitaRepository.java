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
import java.time.LocalTime;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cita;

/**
 * Spring Data JPA OwnerRepository interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface CitaRepository extends Repository<Cita, Integer> {

	void save(Cita cita) throws DataAccessException;

	@Query("SELECT DISTINCT cita FROM Cita cita left join fetch cita.horaCita WHERE cita.fechaCita LIKE :fechaCita%")
	public Collection<Cita> findByFechaCita(@Param("fechaCita") LocalDate fechaCita);

	@Query("SELECT cita FROM Cita cita left join fetch cita.fechaCita, cita.horaCita WHERE cita.id =:id")
	public Cita findById(@Param("id") int id);
	
	@Query("SELECT DISTINCT cita FROM Cita cita left join fetch cita.horaCita WHERE cita.fechaCita LIKE :fechaCita && cita.horaCita =: cita.horaCita%")
	public Collection<Cita> findByFechaCitaAndHoraCita(@Param("fechaCita") LocalDate fechaCita,@Param("horaCita") LocalTime horaCita);
	
	Collection<Cita> findAll() throws DataAccessException;

}
