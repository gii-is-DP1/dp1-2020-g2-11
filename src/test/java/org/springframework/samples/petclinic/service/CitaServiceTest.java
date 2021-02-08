package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.service.exceptions.NotPropertyException;
import org.springframework.samples.petclinic.service.exceptions.SobrecargaDeVehiculosException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CitaServiceTest {
	@Autowired
	protected ClienteService clienteService;
	@Autowired
	protected VehiculoService vehiculoService;
	@Autowired
	protected CitaService citaService;
	private Cita cita;

	@Test
	@Transactional
	public void shouldSaveCita() throws DataAccessException, SobrecargaDeVehiculosException, NotPropertyException {

		cita = new Cita();
		cita.setId(5);
		cita.setFechaCita(LocalDate.of(2021, 1, 25));
		cita.setHoraCita(LocalTime.of(9, 30));
		cita.setCliente(clienteService.findClienteById(1).get());
		cita.setVehiculo(vehiculoService.findVehiculoById(1));
		this.citaService.saveCita(cita);
		Cita citas = citaService.findCitaById(5);
		assertThat(citas).isNotNull();
	}

	@Test
	@Transactional
	public void shouldFindCitaByFecha() throws DataAccessException, SobrecargaDeVehiculosException {
		cita = new Cita();
		cita.setId(5);
		cita.setFechaCita(LocalDate.of(2021, 01, 25));
		cita.setHoraCita(LocalTime.of(9, 30));
		cita.setCliente(clienteService.findClienteById(1).get());
		cita.setVehiculo(vehiculoService.findVehiculoById(1));
		this.citaService.saveCita(cita);
		Collection<Cita> citas = citaService.findCitaByFechaCita(LocalDate.of(2021, 01, 25));
		assertThat(citas.size()).isEqualTo(1);
	}

	@Test
	@Transactional
	public void shouldFindCitasByCliente() throws DataAccessException, SobrecargaDeVehiculosException {
		cita = new Cita();
		cita.setId(5);
		cita.setFechaCita(LocalDate.of(2021, 01, 25));
		cita.setHoraCita(LocalTime.of(9, 30));
		cita.setCliente(clienteService.findClienteById(1).get());
		cita.setVehiculo(vehiculoService.findVehiculoById(1));
		this.citaService.saveCita(cita);
		Collection<Cita> citas = citaService.findCitasByCliente(1);
		assertThat(citas.size()).isNotNull();
	}

	@Test
	@Transactional
	public void shouldDeleteCita() throws DataAccessException, SobrecargaDeVehiculosException {
		cita = new Cita();
		cita.setId(5);
		cita.setFechaCita(LocalDate.of(2021, 01, 25));
		cita.setHoraCita(LocalTime.of(9, 30));
		cita.setCliente(clienteService.findClienteById(1).get());
		cita.setVehiculo(vehiculoService.findVehiculoById(1));
		this.citaService.saveCita(cita);
		this.citaService.removeCita(5);
		assertThat(citaService.findAllCita().size()).isEqualTo(15);
	}

	@Test
	@Transactional
	public void shouldFindAllCitas() throws DataAccessException, SobrecargaDeVehiculosException {
		cita = new Cita();
		cita.setId(5);
		cita.setFechaCita(LocalDate.of(2021, 01, 25));
		cita.setHoraCita(LocalTime.of(9, 30));
		cita.setCliente(clienteService.findClienteById(1).get());
		cita.setVehiculo(vehiculoService.findVehiculoById(1));
		this.citaService.saveCita(cita);
		assertThat(citaService.findAllCita().size()).isEqualTo(16);
	}

	@Test
	@Transactional
	public void shouldFindCitasById() throws DataAccessException, SobrecargaDeVehiculosException {
		cita = new Cita();
		cita.setId(5);
		cita.setFechaCita(LocalDate.of(2021, 01, 25));
		cita.setHoraCita(LocalTime.of(9, 30));
		cita.setCliente(clienteService.findClienteById(1).get());
		cita.setVehiculo(vehiculoService.findVehiculoById(1));
		this.citaService.saveCita(cita);
		Cita citas = citaService.findCitaById(1);
		assertThat(citas).isNotNull();
	}
}
