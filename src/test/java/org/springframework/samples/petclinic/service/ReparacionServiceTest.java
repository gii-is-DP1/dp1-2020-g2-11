package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Reparacion;
import org.springframework.samples.petclinic.model.Revision;
import org.springframework.samples.petclinic.model.TipoReparacion;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.exceptions.VehiculosAntiguo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ReparacionServiceTest {

	@Autowired
	protected ReparacionService reparacionService;

	private Reparacion reparacion;
	private Vehiculo vehiculo;
	private Revision revision;

	@Test
	@Transactional
	@DisplayName("Inserting new reparacion")
	public void shouldInsertReparacion() throws DataAccessException {
		reparacion = new Reparacion();
		reparacion.setId(1);
		reparacion.setDuracion(120); // en minutos
		reparacion.setPrecio(60.50);
		reparacion.setTipoReparacion(TipoReparacion.MECANICA);

		this.reparacionService.saveReparacion(reparacion);
		Integer reparaciones = this.reparacionService.findReparaciones().size();

		assertThat(reparaciones.equals(1));
	}

//	@Test
//	@Transactional
//	@DisplayName("Updating a reparacion")
//	public void shouldUpdateReparacion() throws DataAccessException {
//		reparacion = new Reparacion();
//		reparacion.setId(1);
//		reparacion.setDuracion(120); // en minutos
//		reparacion.setPrecio(60.50);
//		reparacion.setTipoReparacion(TipoReparacion.MECANICA);
//
//		this.reparacionService.saveReparacion(reparacion);
//		reparacion.setPrecio(70.00);
//		this.reparacionService.updateReparacion(reparacion);
//		assertThat(reparacion).isNull();
//	}

	@Test
	@Transactional
	@DisplayName("Finding reparaciones")
	public void shouldFindReparaciones() throws DataAccessException {
		reparacion = new Reparacion();
		reparacion.setId(1);
		reparacion.setDuracion(120); // en minutos
		reparacion.setPrecio(60.50);
		reparacion.setTipoReparacion(TipoReparacion.MECANICA);

		this.reparacionService.saveReparacion(reparacion);
		Collection<Reparacion> reparaciones = this.reparacionService.findReparaciones();
		assertThat(reparaciones.size()).isEqualTo(1);
	}

	@Test
	@Transactional
	@DisplayName("Finding reparacion by id")
	public void shouldFindReparacionById() throws DataAccessException {
		reparacion = new Reparacion();
		reparacion.setId(1);
		reparacion.setDuracion(120); // en minutos
		reparacion.setPrecio(60.50);
		reparacion.setTipoReparacion(TipoReparacion.MECANICA);

		this.reparacionService.saveReparacion(reparacion);
		Reparacion reparacion = this.reparacionService.findReparacionById(1);
		assertThat(reparacion).isNull();
	}

	public void shouldFindVehículoByTipo() throws DataAccessException, VehiculosAntiguo {
		vehiculo = new Vehiculo();
		vehiculo.setFechaFabricacion(LocalDate.of(02, Month.SEPTEMBER, 2010));
		vehiculo.setKilometraje(130000);
		vehiculo.setMatricula("1234FCD");
		vehiculo.setTipoVehiculo(TipoVehiculo.COCHE);

		this.reparacionService.saveVehiculo(vehiculo);
		Collection<Vehiculo> vehiculos = this.reparacionService.findVehículoByTipo(TipoVehiculo.COCHE);
		assertThat(vehiculos).isEqualTo(1);
	}

	public void shouldFindVehiculos() throws DataAccessException, VehiculosAntiguo {
		vehiculo = new Vehiculo();
		vehiculo.setFechaFabricacion(LocalDate.of(2010, Month.SEPTEMBER, 02));
		vehiculo.setKilometraje(130000);
		vehiculo.setMatricula("1234FCD");
		vehiculo.setTipoVehiculo(TipoVehiculo.COCHE);

		this.reparacionService.saveVehiculo(vehiculo);
		Collection<Vehiculo> vehiculos = this.reparacionService.findVehiculos();
		assertThat(vehiculos.size()).isEqualTo(1);
	}

	public void shouldDeleteVehiculos() throws DataAccessException, VehiculosAntiguo {
		vehiculo = new Vehiculo();
		vehiculo.setFechaFabricacion(LocalDate.of(02, Month.SEPTEMBER, 2010));
		vehiculo.setKilometraje(130000);
		vehiculo.setMatricula("1234FCD");
		vehiculo.setTipoVehiculo(TipoVehiculo.COCHE);

		this.reparacionService.saveVehiculo(vehiculo);
		this.reparacionService.deleteVehiculo("1234FCD");
		Collection<Vehiculo> vehiculos = this.reparacionService.findVehiculos();
		assertThat(vehiculos.size()).isEqualTo(0);
	}

	public void shouldFindRevision() throws DataAccessException {
		revision.setDescripcion("Fallo en correa");
		revision.setDuracion(30); //en minutos
		revision.setFechaRevision(LocalDate.of(2020, Month.NOVEMBER, 26));
		revision.setId(3);
		
		this.reparacionService.saveRevision(revision);
		Collection<Revision> revisiones = this.reparacionService.findRevisionByFecha(LocalDate.of(2020, Month.NOVEMBER, 26));
		assertThat(revisiones.size()).isEqualTo(1);
	}
	
	public void shouldDeleteRevision() throws DataAccessException {
		revision.setDescripcion("Fallo en correa");
		revision.setDuracion(30); //en minutos
		revision.setFechaRevision(LocalDate.of(2020, Month.NOVEMBER, 26));
		revision.setId(3);
		
		this.reparacionService.saveRevision(revision);
		this.reparacionService.deleteRevision(3);
		Collection<Revision> revisiones = this.reparacionService.findRevisionByFecha(LocalDate.of(2020, Month.NOVEMBER, 26));
		assertThat(revisiones.size()).isEqualTo(0);
	}
}
