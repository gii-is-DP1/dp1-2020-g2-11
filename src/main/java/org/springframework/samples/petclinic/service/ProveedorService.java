package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.repository.PedidoRepository;
import org.springframework.samples.petclinic.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorService {
	
	private ProveedorRepository proveedorRepository;
	
	@Autowired
	public ProveedorService(ProveedorRepository proveedorRepository, PedidoRepository pedidoRepository) {
		this.proveedorRepository = proveedorRepository;
	}
	
	@Transactional
	public void saveProveedor(Proveedor proveedor) throws DataAccessException {
		proveedor.setDisponible(true);
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
	public void deleteProveedor(Integer id) throws DataAccessException {
		proveedorRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Proveedor findProveedorById(Integer id) throws DataAccessException {
		return proveedorRepository.findById(id).get();
	}
	@Transactional(readOnly = true)	
	public Collection<Proveedor> findProveedoresDisponibles() throws DataAccessException {
		return proveedorRepository.findDisponibles(true);
	}
	@Transactional
	public void findProveedorNoDisponibles(Integer id) throws DataAccessException {
		Proveedor proveedor= proveedorRepository.findById(id).get();
		proveedor.setDisponible(false);
	}
}
