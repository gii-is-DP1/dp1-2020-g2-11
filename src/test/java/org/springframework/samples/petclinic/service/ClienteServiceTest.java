package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Optional;

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
class ClienteServiceTest {

	@Autowired
	protected ClienteService clienteService;
	private Cliente cliente;
	private User user;
	
   	void cliente() throws DataAccessException {
   	}
   	
	@Test
	@Transactional
	public void shouldInsertCliente() {
		cliente = new Cliente();
		cliente.setNombre("Antonio");
		cliente.setApellidos("López");
		cliente.setDni("57862458K");
		cliente.setId(25);
		cliente.setEmail("antoniolopez96@gmail.com");
		cliente.setTelefono("658748325");
		
		user = new User();
		user.setUsername("antoniord34");
		user.setPassword("julioverne23");
		user.setEnabled(true);
		cliente.setUser(user);
		
		clienteService.saveCliente(cliente);
		Collection<Cliente> clientes = this.clienteService.findClientes();
		assertThat(clientes.size()).isEqualTo(5);
	}   
	
	@Test
	@Transactional
	public void shouldFindCliente() {
		Collection<Cliente> clientes = this.clienteService.findClientes();
		assertThat(clientes.size()).isEqualTo(4);
	}		
    
	@Test
	@Transactional
	public void shouldFindClienteByDni(){
		Cliente cliente = this.clienteService.findClienteByDni("62748364G");
		assertThat(cliente).isNotNull();	
	}
	
	@Test
	@Transactional
	public void shouldFindClienteById(){
		Optional<Cliente> cliente = this.clienteService.findClienteById(1);
		assertThat(cliente).isNotNull();
	}
	
	@Test
	@Transactional
	public void shouldFindClienteByNombre(){
		Collection<Cliente> clientes = this.clienteService.findClienteByNombre("Manuel");
		assertThat(clientes.size()).isEqualTo(2);
	}
	
	@Test
	@Transactional
	public void shouldFindClienteByUsername(){
		Cliente cliente = this.clienteService.findClienteByUsername("cliente1");
		assertThat(cliente).isNotNull();
	}
	

}
