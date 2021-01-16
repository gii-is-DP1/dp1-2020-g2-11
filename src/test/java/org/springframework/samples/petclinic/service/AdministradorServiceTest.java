package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AdministradorServiceTest {

	@Autowired
	protected AdministradorService administradorService;

	@Test
	@Transactional
	public void shouldInsertAdministrador() throws DataAccessException {

		Administrador admin = new Administrador();
		User usuario1 = new User();

		admin.setNombre("Juan");
		admin.setApellidos("Pérez");
		admin.setDni("78862457K");
		admin.setId(2);
		admin.setEmail("juanperez9@gmail.com");
		admin.setTelefono("644895623");

		usuario1.setUsername("admin2");
		usuario1.setPassword("admin2");
		admin.setUser(usuario1);

		this.administradorService.saveAdministrador(admin);
		Collection<Administrador> admins = this.administradorService.findAdministrador();
		assertThat(admins.size()).isEqualTo(2);

	}
}
