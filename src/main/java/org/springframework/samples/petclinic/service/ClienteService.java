package org.springframework.samples.petclinic.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.transaction.annotation.Transactional;

public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteService(ClienteRepository ClienteRepository) {
		this.clienteRepository = ClienteRepository;
	}
	
	@Transactional
	public void saveCliente(Cliente cliente) throws DataAccessException {
		clienteRepository.save(cliente);
	}
	
	
	@Transactional(readOnly = true)	
	public Collection<Cliente> findClientes() throws DataAccessException {
		return clienteRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Collection<Cliente> findClienteByDni(String dni) throws DataAccessException {
		return clienteRepository.findByDni(dni);
	}
	
	@Transactional(readOnly = true)
	public Cliente findClienteById(Integer id) throws DataAccessException {
		return clienteRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Cliente findClienteByNombre(String nombre) throws DataAccessException {
		return clienteRepository.findByNombre(nombre);
	}

	@Transactional(readOnly = true)
    public Cliente findClienteByFactura(Integer id) throws DataAccessException {
        return clienteRepository.findByIdfactura(id);
    }
	
	@Transactional(readOnly = true)
	public void deleteClientebyFactura(Integer id) throws DataAccessException {
		Factura factura = new Factura();
		Cliente cliente = new Cliente();
		Cliente cliente2 = new Cliente();
		cliente2 = findClienteByFactura(id);
		if(factura.getPagado()) 
		if (factura.getCliente().equals(cliente2)) {
		cliente2 = cliente;	
		}else
			System.out.println("Hay facturas sin pagar");
		
		}
	}
	
	

