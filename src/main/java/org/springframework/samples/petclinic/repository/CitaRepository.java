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

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;

/**
 * Spring Data JPA OwnerRepository interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface CitaRepository extends CrudRepository<Cita, Integer> {

	@Query("SELECT DISTINCT c FROM Cita c WHERE c.fechaCita = :fechaCita")
	Collection<Cita> findByFechaCita(@Param("fechaCita") LocalDate fechaCita);
	
	@Query("SELECT DISTINCT c FROM Cita c WHERE c.fechaCita = :fechaCita AND c.horaCita = :horaCita")
	Collection<Cita> findByFechaCitaAndHoraCita(@Param("fechaCita") LocalDate fechaCita,@Param("horaCita") LocalTime horaCita);
	
	@Modifying
	@Query("DELETE FROM Cita c WHERE c.id = :id")
	void remove(@Param("id") Integer id);
	
	@Modifying
	@Query("DELETE FROM Cita c WHERE c.cliente_id = :cliente_id")
	void deleteByCliente(@Param("cliente_id") Integer cliente_id);	
}
