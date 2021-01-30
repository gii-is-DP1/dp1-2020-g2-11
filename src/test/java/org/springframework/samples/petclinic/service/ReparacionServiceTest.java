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
		Collection<Reparacion> reparaciones = this.reparacionService.findReparaciones();
		assertThat(reparaciones.size()).isEqualTo(3);
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
		assertThat(reparacion).isNotNull();
	}

	@Test
	@Transactional
	public void shouldInsertVehiculo() throws DataAccessException, VehiculosAntiguo {
		vehiculo = new Vehiculo();
		vehiculo.setId(5);
		vehiculo.setMatricula("1234FGB");
		vehiculo.setKilometraje(24000);
		vehiculo.setFechaFabricacion(LocalDate.of(2017, Month.NOVEMBER, 20));
		vehiculo.setTipoVehiculo(TipoVehiculo.COCHE);

		this.reparacionService.saveVehiculo(vehiculo);
		Collection<Vehiculo> vehiculos = reparacionService.findVehiculos();
		assertThat(vehiculos.size()).isEqualTo(5);

	}

	@Test
	@Transactional
	public void shouldFindVehículoByTipo() throws DataAccessException, VehiculosAntiguo {
		Collection<Vehiculo> vehiculos = this.reparacionService.findVehículoByTipo(TipoVehiculo.COCHE);
		assertThat(vehiculos).isEqualTo(2);
	}

	@Test
	@Transactional
	public void shouldFindVehiculos() throws DataAccessException, VehiculosAntiguo {
		Collection<Vehiculo> vehiculos = this.reparacionService.findVehiculos();
		assertThat(vehiculos.size()).isEqualTo(4);
	}

	@Test
	@Transactional
	public void shouldDeleteVehiculos() throws DataAccessException, VehiculosAntiguo {
		this.reparacionService.deleteVehiculo(1);
		Collection<Vehiculo> vehiculos = this.reparacionService.findVehiculos();
		assertThat(vehiculos.size()).isEqualTo(3);
	}

	@Test
	@Transactional
	public void shouldInsertRevision() {
		revision = new Revision();
		revision.setId(4);
		revision.setDescripcion("Fallo en trasmision");
		revision.setDuracion(20);
		revision.setFechaRevision(LocalDate.of(2020, Month.DECEMBER, 12));

		this.reparacionService.saveRevision(revision);
		Collection<Revision> revisiones = this.reparacionService
				.findRevisionByFecha(LocalDate.of(2020, Month.DECEMBER, 12));
		assertThat(revisiones.size()).isEqualTo(1);
	}

	@Test
	@Transactional
	public void shouldFindRevision() throws DataAccessException {
		Collection<Revision> revisiones = this.reparacionService
				.findRevisionByFecha(LocalDate.of(2020, Month.DECEMBER, 11));
		assertThat(revisiones.size()).isEqualTo(2);
	}

	@Test
	@Transactional
	public void shouldDeleteRevision() throws DataAccessException {
		reparacionService.deleteRevision(3);
		Collection<Revision> revisiones = this.reparacionService
				.findRevisionByFecha(LocalDate.of(2020, Month.DECEMBER, 11));
		assertThat(revisiones.size()).isEqualTo(0);
	}
}
