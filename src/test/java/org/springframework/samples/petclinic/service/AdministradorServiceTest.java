package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters=@ComponentScan.Filter(Service.class))
public class AdministradorServiceTest {
	
	@Autowired
	protected AdministradorService administradorService;
	
	
	@Test
	@Transactional
	public void shouldInsertAdministrador() {

		Administrador admin = new Administrador();
		admin.setNombre("Paco");
		admin.setApellidos("Garcia");
		admin.setTelefono("683020234");
		admin.setEmail("paco@gmail.com");
		User usuario = new User();
		usuario.setUsername("Paqui");
		usuario.setPassword("Memento");
		usuario.setEnabled(true);
		admin.setUser(usuario);                
                
		this.administradorService.saveAdministrador(admin);
		Administrador administradores = this.administradorService.;
		assertThat(administradores).isNotNull();
	}
	
	@Test
	@Transactional
	void shouldUpdateAdministrador() {
		Administrador admin = this.administradorService.findAdministrador();
		String oldNombre = admin.getNombre();
		String newNombre = oldNombre+"Yeah";
		
		admin.setNombre(newNombre);
		this.administradorService.saveAdministrador(admin);
		
		admin = this.administradorService.findAdministrador();
		assertThat(admin.getNombre()).isEqualTo(newNombre);
	}
}

