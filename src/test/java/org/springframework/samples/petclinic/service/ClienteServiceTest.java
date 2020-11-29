package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ClienteServiceTest {

	@Autowired
	protected ClienteService clienteService;
	
	@Autowired
	private Cliente cliente;
	private User user;
	
   	@BeforeEach
	void setup() {

		cliente = new Cliente();
		cliente.setNombre("Antonio");
		cliente.setApellidos("López");
		cliente.setDni("578624578K");
		cliente.setId(25);
		cliente.setEmail("antoniolopez96@gmail.com");
		cliente.setTelefono("658748325");
		user.setUsername("antoniord34");
		user.setPassword("julioverne23");
		cliente.setUser(user);
		

	}   
	
	@Test
	@Transactional
	void nuevocliente() throws DataAccessException {
	this.clienteService.saveCliente(cliente);			
	Collection<Cliente> clientes = this.clienteService.findClientes();
	assertThat(clientes.size()).isEqualTo(1);
	}

}
