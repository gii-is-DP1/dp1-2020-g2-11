package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cita;

public interface CitaRepository extends CrudRepository<Cita, Integer> {

	@Query("SELECT DISTINCT c FROM Cita c WHERE c.fechaCita = :fechaCita")
	Collection<Cita> findByFechaCita(@Param("fechaCita") LocalDate fechaCita);
	
	@Query("SELECT DISTINCT c FROM Cita c WHERE c.fechaCita = :fechaCita AND c.horaCita = :horaCita")
	Collection<Cita> findByFechaCitaAndHoraCita(@Param("fechaCita") LocalDate fechaCita,@Param("horaCita") LocalTime horaCita);
	
	@Modifying
	@Query("DELETE FROM Cita c WHERE c.id = :id")
	void remove(@Param("id") Integer id);
	
	@Query("SELECT c FROM Cita c WHERE c.cliente.id = :clienteId")
	Collection<Cita> findByCliente(@Param("clienteId") Integer clienteId);
}
