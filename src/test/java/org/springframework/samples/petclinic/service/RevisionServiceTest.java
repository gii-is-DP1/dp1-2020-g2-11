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
import org.springframework.samples.petclinic.model.Revision;
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
	public void shouldFindByFechaRevision() throws DataAccessException {
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
		revision = new Revision();
		revision.setId(6);
		revision.setDescripcion("Fallo en trasmision");
		revision.setDuracion(20);
		revision.setFechaRevision(LocalDate.of(2020, Month.DECEMBER, 12));

		this.revisionService.saveRevision(revision);
		revisionService.deleteRevision(6);
		Collection<Revision> revisiones = this.revisionService.findAllRevisiones();
		assertThat(revisiones.size()).isEqualTo(5);
	}
}
