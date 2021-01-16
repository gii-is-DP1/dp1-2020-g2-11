package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.samples.petclinic.service.exceptions.NoPagadaException;
import org.springframework.samples.petclinic.service.exceptions.TipoPagoException;
import org.springframework.stereotype.Service;
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class FacturaServiceTest {
	@Autowired
	protected FacturaService facturaService;
	@Autowired
	protected ClienteService clienteService;
	private Factura factura;
	
	
	 
	
	
	@Test
	@Transactional
	public void nuevaFacturaTest() throws DataAccessException, TipoPagoException {
		
		factura = new Factura();
		factura.setCliente(clienteService.findClienteById(1).get());
		factura.setDescripcion("p");
		factura.setFechaEmision(LocalDate.of(2020, 8, 12));
		factura.setId(5);
		factura.setPagado(false);
		factura.setPrecio(12.20);
		factura.setTipoPago(TipoPago.EFECTIVO);
		
		facturaService.saveFactura(factura);
		Collection<Factura> facturas = facturaService.findFacturas();
		assertThat(facturas.size()).isEqualTo(5);
			
	}
	
	@Test
	@Transactional
	public void deleteFacturaTest() throws DataAccessException, TipoPagoException, NoPagadaException {
		
		facturaService.deleteFactura(1);
		Collection<Factura> facturas = this.facturaService.findFacturas();
		assertThat(facturas.size()).isEqualTo(3);
	}
}
