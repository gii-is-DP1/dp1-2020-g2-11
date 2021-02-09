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
import org.springframework.samples.petclinic.model.Mecanico;
import org.springframework.samples.petclinic.model.Revision;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class RevisionServiceTest {

	@Autowired
	protected RevisionService revisionService;
	private Revision revision;

	@Test
	@Transactional
	public void shouldInsertRevision() {
		revision = new Revision();
		revision.setId(4);
		revision.setDescripcion("Fallo en trasmision");
		revision.setDuracion(20);
		revision.setFechaRevision(LocalDate.of(2020, Month.DECEMBER, 12));

		this.revisionService.saveRevision(revision);
		Collection<Revision> revisiones = this.revisionService
				.findRevisionByFecha(LocalDate.of(2020, Month.DECEMBER, 12));
		assertThat(revisiones.size()).isEqualTo(1);
	}

	@Test
	@Transactional
	public void shouldFindByFechaRevisionByFecha() throws DataAccessException {
		revision = new Revision();
		revision.setId(4);
		revision.setDescripcion("Fallo en trasmision");
		revision.setDuracion(20);
		revision.setFechaRevision(LocalDate.of(2020, Month.DECEMBER, 12));

		this.revisionService.saveRevision(revision);
		Collection<Revision> revisiones = this.revisionService
				.findRevisionByFecha(LocalDate.of(2020, Month.DECEMBER, 12));
		assertThat(revisiones.size()).isEqualTo(1);
	}

	@Test
	@Transactional
	public void shouldDeleteRevision() throws DataAccessException {
		Collection<Revision> revisionesPreview = this.revisionService.findAllRevisiones();
		revisionService.deleteRevision(1);
		Collection<Revision> revisiones = this.revisionService.findAllRevisiones();
		assertThat(revisiones.size()+1).isEqualTo(revisionesPreview.size());
	}
	@Test
	@Transactional
	public void shouldFindRevisiones() throws DataAccessException {
		revision = new Revision();
		revision.setId(6);
		revision.setDescripcion("Fallo en trasmision");
		revision.setDuracion(20);
		revision.setFechaRevision(LocalDate.of(2020, Month.DECEMBER, 12));
		this.revisionService.saveRevision(revision);
		Collection<Revision> revisiones = this.revisionService.findAllRevisiones();
		assertThat(revisiones.size()).isNotNull();

	}
	@Test
	@Transactional
	public void shouldAsignRevision() throws DataAccessException {
		Mecanico mecanico1 = new Mecanico();
        mecanico1.setNombre("Juan");
        mecanico1.setApellidos("Pérez");
        mecanico1.setDni("78862457K");
        mecanico1.setId(2);
        mecanico1.setEmail("juanperez9@gmail.com");
        mecanico1.setTelefono("644895623");        
        Revision revision = this.revisionService.findRevisionById(1).get();
        revision.setMecanico(mecanico1);
		
		
		assertThat(revision.getMecanico()).isEqualTo(mecanico1);
	}
	@Test
	@Transactional
	public void shouldFindRevisionByFecha() throws DataAccessException {
		revision = new Revision();
		revision.setId(6);
		revision.setDescripcion("Fallo en trasmision");
		revision.setDuracion(20);
		revision.setFechaRevision(LocalDate.of(2020, Month.DECEMBER, 12));
		Collection<Revision> revisiones = this.revisionService.findRevisionByFecha(LocalDate.of(2020, Month.DECEMBER, 12));
		assertThat(revisiones.size()).isNotNull();
	}
	@Test
	@Transactional
	public void shouldFindRevisionesDisponibles() throws DataAccessException {
		revision = new Revision();
		revision.setId(6);
		revision.setDescripcion("Fallo en trasmision");
		revision.setDuracion(20);
		revision.setFechaRevision(LocalDate.of(2020, Month.DECEMBER, 12));
		revision.setAsignada(false);
		Collection<Revision> revisiones = this.revisionService.findAllRevisionesDisponibles();
		assertThat(revisiones.size()).isNotNull();

	}
}
