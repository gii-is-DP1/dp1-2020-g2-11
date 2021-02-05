package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.samples.petclinic.repository.FacturaRepository;
import org.springframework.samples.petclinic.service.exceptions.NoPagadaException;
import org.springframework.samples.petclinic.service.exceptions.TipoPagoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacturaService {

	private FacturaRepository facturaRepository;
	
	@Autowired
	public FacturaService(FacturaRepository facturaRepository) {
		this.facturaRepository = facturaRepository;
	}

	@Transactional(readOnly = true)
	public Optional<Factura> findFacturabyId(Integer id) throws DataAccessException {
		return facturaRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Factura> findFacturabyFechaEmision(LocalDate fecha) throws DataAccessException {
		return facturaRepository.findByFechaEmision(fecha);
	}

	@Transactional(readOnly = true)
	public Collection<Factura> findFacturaPagado(Boolean pagado) throws DataAccessException {
		return facturaRepository.findByPagado(pagado);
	}

	@Transactional
	public void saveFactura(Factura factura) throws DataAccessException, TipoPagoException {
		TipoPago tpago = factura.getTipoPago();
		Double precio = factura.getPrecio();
		if (precio > 300 && tpago == TipoPago.EFECTIVO) {
			throw new TipoPagoException();
		} else {
			facturaRepository.save(factura);
		}
	}

//	@Transactional
//	public void updateFactura(Factura factura) throws DataAccessException {
//		facturaRepository.update(factura);
//	}
	@Transactional
	public void setFacturaPagado(Integer id){
		Factura factura= facturaRepository.findById(id).get();
		factura.setPagado(true);
		
	}

	@Transactional(readOnly = true)
	public Collection<Factura> findFacturas() throws DataAccessException {
		return (Collection<Factura>) facturaRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Set<Factura> findMisfacturas(Integer id) throws DataAccessException {
		return (Set<Factura>) facturaRepository.findByCliente(id);
	}

	@Transactional
	public void deleteFactura(Integer id) throws NoPagadaException {
		
			if (findFacturabyId(id).get().getPagado() == false) {
				throw new NoPagadaException();
			} else {
				facturaRepository.deleteById(id);
			}
		

	}
}
