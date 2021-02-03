package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.samples.petclinic.service.exceptions.LimitePagoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public ClienteService(ClienteRepository ClienteRepository) {
		this.clienteRepository = ClienteRepository;
	}

	@Transactional
	public void saveCliente(Cliente cliente) throws DataAccessException {
		clienteRepository.save(cliente);
		userService.saveUser(cliente.getUser());
		authoritiesService.saveAuthorities(cliente.getUser().getUsername(), "cliente");
	}

	@Transactional(readOnly = true)
	public Collection<Cliente> findClientes() throws DataAccessException {
		return (Collection<Cliente>) clienteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Cliente findClienteByDni(String dni) throws DataAccessException {
		return clienteRepository.findByDni(dni);
	}

	@Transactional(readOnly = true)
	public Optional<Cliente> findClienteById(Integer id) throws DataAccessException {
		return clienteRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Cliente> findClienteByNombre(String nombre) throws DataAccessException {
		if(nombre == "") {
			return (Collection<Cliente>) clienteRepository.findAll();
		}
		return clienteRepository.findByNombre(nombre);
	}

	@Transactional(readOnly = true)
	public Cliente findClienteByUsername(String username) throws DataAccessException {
		return clienteRepository.findByUsername(username);
	}
	
//	@Transactional(readOnly = true)
//	public Cliente findClienteByFactura(Integer idFactura) throws DataAccessException {
//		return clienteRepository.findByIdFactura(idFactura);
//	}
//
//	@Transactional(readOnly = true)
//	public void deleteClientebyFactura(Integer id) throws DataAccessException {
//		Factura factura = new Factura();
//		Cliente cliente = new Cliente();
//		Cliente cliente2 = new Cliente();
//		cliente2 = findClienteByFactura(id);
//		if (factura.getPagado())
//			if (factura.getCliente().equals(cliente2)) {
//				cliente2 = cliente;
//			} else
//				System.out.println("Hay facturas sin pagar");
//
//	}

	@Transactional
	public void saveClientebyFactura(Cliente cliente) throws DataAccessException, LimitePagoException {
		Factura factura = new Factura();
		factura.getPagado();
		if (Boolean.FALSE && factura.getFechaEmision().compareTo(LocalDate.now()) >= 15) {
			clienteRepository.save(cliente);
			throw new LimitePagoException();
		} else {
			clienteRepository.delete(cliente);

		}
	}
}
