package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.repository.MecanicoRepository;
import org.springframework.samples.petclinic.repository.PedidoRepository;
import org.springframework.samples.petclinic.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorService {
	
	private ProveedorRepository proveedorRepository;
	private PedidoRepository pedidoRepository;
	
	@Autowired
	public ProveedorService(ProveedorRepository proveedorRepository, PedidoRepository pedidoRepository) {
		this.proveedorRepository = proveedorRepository;
		this.pedidoRepository = pedidoRepository;
	}
	
	@Transactional
	public void saveProveedor(Proveedor proveedor) throws DataAccessException {
		proveedorRepository.save(proveedor);
	}
	
	@Transactional(readOnly = true)
	public Collection<Proveedor> findProveedorByNombre(String nombre) throws DataAccessException {
		return proveedorRepository.findByNombre(nombre);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Proveedor> findProveedores() throws DataAccessException {
		return proveedorRepository.findAll();
	}
	
	@Transactional
	public void deleteProducto(String nombre) throws DataAccessException {
		proveedorRepository.delete(nombre);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Pedido> findPedidos() throws DataAccessException {
		return pedidoRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Pedido findPedidobyId(Integer id) throws DataAccessException {
		return pedidoRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Collection<Pedido> findPedidobyFechaPedido(LocalDate fecha) throws DataAccessException {
		return pedidoRepository.findByFechaPedido(fecha);
	}

}
