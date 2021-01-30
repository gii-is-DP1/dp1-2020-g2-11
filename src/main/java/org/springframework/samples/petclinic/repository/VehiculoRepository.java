package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Integer> {

	@Query("SELECT v FROM Vehiculo v WHERE v.matricula = :matricula")
	Collection<Vehiculo> findByMatricula(@Param("matricula") String matricula);

	@Query("SELECT v FROM Vehiculo v WHERE v.tipoVehiculo = :tipoVehiculo")
	Collection<Vehiculo> findByTipoVehiculo(@Param("tipoVehiculo") TipoVehiculo tipoVehiculo);
}
