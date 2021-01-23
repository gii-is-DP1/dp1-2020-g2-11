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
import org.springframework.samples.petclinic.model.Estancia;
import org.springframework.samples.petclinic.service.exceptions.SobrecargaDeVehiculosException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters=@ComponentScan.Filter(Service.class))
public class CitaServiceTest {
	
	@Autowired
	protected CitaService citaService;
	private Cita cita;
	
	@Test
	@Transactional
	public void shouldInsertCita() throws DataAccessException, SobrecargaDeVehiculosException{
		
		cita = new Cita();
		cita.setId(5);
		cita.setFechaCita(LocalDate.of(2021, 1, 25));
		cita.setHoraCita(LocalTime.of(9, 30));
		
		this.citaService.saveCita(cita);
		Collection<Cita> citas = citaService.findAllCita();
		assertThat(citas.size()).isEqualTo(5);
	}
	
	@Test
	@Transactional
	public void shouldFindCitaByFecha() {
		Collection<Cita> citas = citaService.findCitaByFechaCita(LocalDate.of(2021, 01, 04));
		assertThat(citas).isNotNull();
	}
	
//	@Test
//	@Transactional
//	public void shouldFindEstacionados() {
//		Collection<Estancia> estacionados = citaService.findAllEstancia();
//		assertThat(estacionados.size()).isEqualTo(4);
//	}
	
	@Test
	@Transactional
	public void shouldDeleteCita() throws DataAccessException {
		
		this.citaService.removeCita(3);
		assertThat(citaService.findAllCita().size()).isEqualTo(3);
	}
}
