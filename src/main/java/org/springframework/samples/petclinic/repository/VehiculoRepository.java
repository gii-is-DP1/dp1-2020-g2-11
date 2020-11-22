package org.springframework.samples.petclinic.repository;
import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;

public interface VehiculoRepository {
	
	void save(Vehiculo vehiculo) throws DataAccessException;
	void delete(Vehiculo vehiculo) throws DataAccessException;
	
	@Query("SELECT DISTINTC vehiculo FROM Vehiculo vehiculo WHERE vehiculo.matricula:=matricula")
	public Collection<Vehiculo> findByMatricula(@Param("matricula") String matricula);
	
	@Query("SELECT DISTINTC vehiculo FROM Vehiculo vehiculo WHERE vehiculo.tipoVehiculo:=tipoVehiculo")
	public Collection<Vehiculo> findByTipoVehiculo(@Param("tipoVehiculo") TipoVehiculo tipoVehiculo);

	Collection<Vehiculo> findAll() throws DataAccessException;
}
