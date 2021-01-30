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
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class EstanciaServiceTest {
	@Autowired
	protected EstanciaService estanciaService;
	private Estancia estancia;

	@Test
	@Transactional
	public void shouldInsertEstancia() throws DataAccessException, SobrecargaDeVehiculosException {

		estancia = new Estancia();
		estancia.setId(7);
		estancia.setFechaEntrada(LocalDate.of(2021, 1, 25));
		estancia.setHoraEntrada(LocalTime.of(9, 30));
		estancia.setFechaSalida(LocalDate.of(2021, 1, 27));
		estancia.setHoraSalida(LocalTime.of(10, 30));
		

		this.estanciaService.saveEstancia(estancia);
		Collection<Estancia> estancias = estanciaService.findAllEstancia();
		assertThat(estancias.size()).isEqualTo(5);
	}

	@Test
	@Transactional
	public void shouldFindEstacionados() throws DataAccessException{
		Collection<Estancia> estancias = estanciaService.findEstanciasActuales();
		assertThat(estancias.size()).isEqualTo(4);
	}

	@Test
	@Transactional
	public void shouldFindAllEstancias() throws DataAccessException{
		Collection<Estancia> estancias = estanciaService.findAllEstancia();
		assertThat(estancias.size()).isEqualTo(4);
	}
	
	
	
}
