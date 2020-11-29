package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.exceptions.LimitePagoException;
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

		Cliente cliente = new Cliente();
		cliente.setNombre("Antonio");
		cliente.setApellidos("López");
		cliente.setDni("578624578K");
		cliente.setId(25);
		cliente.setEmail("antoniolopez96@gmail.com");
		cliente.setTelefono("658748325");
		user.setUsername("antoniord34");
		user.setPassword("julioverne23");
		cliente.setUser(user);
		
		clienteService.saveCliente(cliente);
		assertThat(cliente.equals(cliente));

	}   
	
	@Test
	@Transactional
	public void shouldFindCliente() {
		Cliente cliente = new Cliente();
		cliente.setNombre("Antonio");
		cliente.setApellidos("López");
		cliente.setDni("578624578K");
		cliente.setId(25);
		cliente.setEmail("antoniolopez96@gmail.com");
		cliente.setTelefono("658748325");
		user.setUsername("antoniord34");
		user.setPassword("julioverne23");
		cliente.setUser(user);
		
		clienteService.saveCliente(cliente);
		Collection<Cliente> clientes = this.clienteService.findClientes();
		assertThat(clientes.size()).isEqualTo(1);
	}		
    
	@Test
	@Transactional
	public void shouldFindClienteByDni(){
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Antonio");
		cliente.setApellidos("López");
		cliente.setDni("578624578K");
		cliente.setId(25);
		cliente.setEmail("antoniolopez96@gmail.com");
		cliente.setTelefono("658748325");
		user.setUsername("antoniord34");
		user.setPassword("julioverne23");
		cliente.setUser(user);
		
		clienteService.saveCliente(cliente);
		Collection<Cliente> clientes = this.clienteService.findClienteByDni("578624578K");
		assertThat(clientes.size()).isEqualTo(1);	
	}
	
	@Test
	@Transactional
	public void shouldFindClienteById(){
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Antonio");
		cliente.setApellidos("López");
		cliente.setDni("578624578K");
		cliente.setId(25);
		cliente.setEmail("antoniolopez96@gmail.com");
		cliente.setTelefono("658748325");
		user.setUsername("antoniord34");
		user.setPassword("julioverne23");
		cliente.setUser(user);
		
		clienteService.saveCliente(cliente);
		Cliente clientes = this.clienteService.findClienteById(25);
		assertThat(clientes.getId()).isNull();
	}
	
	@Test
	@Transactional
	public void shouldFindClienteByNombre(){
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Antonio");
		cliente.setApellidos("López");
		cliente.setDni("578624578K");
		cliente.setId(25);
		cliente.setEmail("antoniolopez96@gmail.com");
		cliente.setTelefono("658748325");
		user.setUsername("antoniord34");
		user.setPassword("julioverne23");
		cliente.setUser(user);
		
		clienteService.saveCliente(cliente);
		Cliente clientes = this.clienteService.findClienteByNombre("Antonio");
		assertThat(clientes.getNombre()).isNotNull();
	}
	
	@Test
	@Transactional
	public void shouldFindClienteByFactura(){
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Antonio");
		cliente.setApellidos("López");
		cliente.setDni("578624578K");
		cliente.setId(25);
		cliente.setEmail("antoniolopez96@gmail.com");
		cliente.setTelefono("658748325");
		user.setUsername("antoniord34");
		user.setPassword("julioverne23");
		cliente.setUser(user);
		
		clienteService.saveCliente(cliente);
		Cliente clientes = this.clienteService.findClienteByFactura(25);
		assertThat(clientes.getFactura()).isNotNull();
	}

	@Test
	@Transactional
	public void shouldDeleteClientebyFactura() throws DataAccessException, LimitePagoException{
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Antonio");
		cliente.setApellidos("López");
		cliente.setDni("578624578K");
		cliente.setId(25);
		cliente.setEmail("antoniolopez96@gmail.com");
		cliente.setTelefono("658748325");
		user.setUsername("antoniord34");
		user.setPassword("julioverne23");
		cliente.setUser(user);
		
		clienteService.saveClientebyFactura(cliente);
		clienteService.deleteClientebyFactura(25);
		assertThat(clienteService.findClienteByNombre("Antonio")).isNull();
	}
	
	
	@Test
	@Transactional
	public void shouldSaveClientebyFactura() throws DataAccessException, LimitePagoException{
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Antonio");
		cliente.setApellidos("López");
		cliente.setDni("578624578K");
		cliente.setId(25);
		cliente.setEmail("antoniolopez96@gmail.com");
		cliente.setTelefono("658748325");
		user.setUsername("antoniord34");
		user.setPassword("julioverne23");
		cliente.setUser(user);
		
		clienteService.saveClientebyFactura(cliente);
		assertThat(cliente.equals(cliente));
	}
	
	
}
