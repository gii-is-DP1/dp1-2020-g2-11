package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.repository.FacturaRepository;
import org.springframework.transaction.annotation.Transactional;

public class FacturaService {
	
	private FacturaRepository facturaRepository;
	

	@Autowired
	public FacturaService(FacturaRepository facturaRepository) {
		this.facturaRepository = facturaRepository; 
	}	
	
	@Transactional(readOnly = true)
	public Factura findFacturabyId(Integer id) throws DataAccessException {
		return facturaRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Factura findFacturabyFechaEmision(LocalDate fecha) throws DataAccessException {
		return facturaRepository.findByFechaEmision(fecha);
	}
	
	@Transactional
	public void saveFactura(Factura factura) throws DataAccessException {
		facturaRepository.save(factura);
	}
	
	@Transactional
	public void updateFactura(Factura factura) throws DataAccessException {
		facturaRepository.update(factura);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Factura> findFacturas() throws DataAccessException {
		return facturaRepository.findAll();
	}

}