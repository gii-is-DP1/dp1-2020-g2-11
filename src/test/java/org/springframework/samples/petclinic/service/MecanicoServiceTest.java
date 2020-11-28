package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Mecanico;
import org.springframework.samples.petclinic.model.User;
import org.springframework.transaction.annotation.Transactional;

public class MecanicoServiceTest {
	@Autowired
	protected MecanicoService mecanicoService;
	
	private Mecanico mecanico;
	private User user;
	
   	@BeforeEach
	void setup() {

		mecanico = mecanicoService.findById(1);
		
	}   
	
	@Test
	@Transactional
	void nuevoMecanico() throws DataAccessException {
		
		mecanico = new Mecanico();
		mecanico.setNombre("Juan");
		mecanico.setApellidos("Pérez");
		mecanico.setDni("788624578K");
		mecanico.setId(2);
		mecanico.setEmail("juanperez9@gmail.com");
		mecanico.setTelefono("644895623");
		user.setUsername("juanperez1");
		user.setPassword("jueanperez1");
		mecanico.setUser(user);
		
		this.mecanicoService.saveMecanico(mecanico);
		Collection<Mecanico> mecanicos = this.mecanicoService.findMecanico();
		assertThat(mecanicos.size()).isEqualTo(1);

	}

}
