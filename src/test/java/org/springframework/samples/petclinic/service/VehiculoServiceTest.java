package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.exceptions.VehiculosAntiguo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class VehiculoServiceTest {

	@Autowired
	protected VehiculoService vehiculoService;

		private Vehiculo vehiculo;



//		@Test
//		@Transactional
//		@DisplayName("Updating a reparacion")
//		public void shouldUpdateReparacion() throws DataAccessException {
//			reparacion = new Reparacion();
//			reparacion.setId(1);
//			reparacion.setDuracion(120); // en minutos
//			reparacion.setPrecio(60.50);
//			reparacion.setTipoReparacion(TipoReparacion.MECANICA);
	//
//			this.reparacionService.saveReparacion(reparacion);
//			reparacion.setPrecio(70.00);
//			this.reparacionService.updateReparacion(reparacion);
//			assertThat(reparacion).isNull();
//		}


		@Test
		@Transactional
		public void shouldInsertVehiculo() throws DataAccessException, VehiculosAntiguo {
			vehiculo = new Vehiculo();
			vehiculo.setId(5);
			vehiculo.setMatricula("1234FGB");
			vehiculo.setKilometraje(24000);
			vehiculo.setFechaFabricacion(LocalDate.of(2017, Month.NOVEMBER, 20));
			vehiculo.setTipoVehiculo(TipoVehiculo.COCHE);

			this.vehiculoService.saveVehiculo(vehiculo);
			Collection<Vehiculo> vehiculos = vehiculoService.findVehiculos();
			assertThat(vehiculos.size()).isEqualTo(5);

		}

		@Test
		@Transactional
		public void shouldFindVehículoByTipo() throws DataAccessException, VehiculosAntiguo {
			Collection<Vehiculo> vehiculos = this.vehiculoService.findVehículoByTipo(TipoVehiculo.COCHE);
			assertThat(vehiculos.size()).isEqualTo(2);
		}

		@Test
		@Transactional
		public void shouldFindVehiculos() throws DataAccessException, VehiculosAntiguo {
			Collection<Vehiculo> vehiculos = this.vehiculoService.findVehiculos();
			assertThat(vehiculos.size()).isEqualTo(4);
		}

		@Test
		@Transactional
		public void shouldDeleteVehiculos() throws DataAccessException, VehiculosAntiguo {
			this.vehiculoService.deleteVehiculo(1);
			Collection<Vehiculo> vehiculos = this.vehiculoService.findVehiculos();
			assertThat(vehiculos.size()).isEqualTo(3);
		}



}
