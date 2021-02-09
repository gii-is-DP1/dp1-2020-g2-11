package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Reparacion;
import org.springframework.samples.petclinic.model.TipoReparacion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ReparacionServiceTest {

	@Autowired
	protected ReparacionService reparacionService;

	private Reparacion reparacion;
	
	

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

	@Test
	@Transactional
	@DisplayName("Finding reparaciones")
	public void shouldFindReparaciones() throws DataAccessException {
		reparacion = new Reparacion();
		reparacion.setId(5);
		reparacion.setDuracion(130); // en minutos
		reparacion.setPrecio(61.50);
		reparacion.setTipoReparacion(TipoReparacion.ELECTRICA);
		
		this.reparacionService.saveReparacion(reparacion);
		Collection<Reparacion> reparaciones = this.reparacionService.findReparaciones();
		assertThat(reparaciones.size()).isEqualTo(4);
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
		Reparacion reparacion = this.reparacionService.findReparacionById(1).get();
		assertThat(reparacion).isNotNull();
	}

	@Test
	@Transactional
	@DisplayName("Deleting reparacion by id")
	public void shouldDeleteReparacionById() throws DataAccessException {

		Collection<Reparacion> reparacionPreview=reparacionService.findReparaciones();
		this.reparacionService.deleteReparacion(1);
		Collection<Reparacion> reparacion=reparacionService.findReparaciones();
		assertThat(reparacionPreview.size()).isEqualTo(reparacion.size()+1);
	}
	





	
}
