package org.springframework.samples.petclinic.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.samples.petclinic.service.exceptions.NoPagadaException;
import org.springframework.samples.petclinic.service.exceptions.TipoPagoException;

public class FacturaServiceTest {
	@Autowired
	FacturaService facturaService;
	Factura factura;
	Cliente cliente;
	
	@Test
	@Transactional
	void nuevaFacturaTest() throws DataAccessException, TipoPagoException {
		cliente = new Cliente();
		cliente.setNombre("Antonio");
		cliente.setApellidos("López");
		cliente.setDni("578624578K");
		cliente.setId(25);
		cliente.setEmail("antoniolopez96@gmail.com");
		cliente.setTelefono("658748325");
		
		factura = new Factura();
		factura.setCliente(cliente);
		factura.setDescripcion("p");
		factura.setFechaEmision(LocalDate.parse("12/8/2020"));
		factura.setId(1);
		factura.setPagado(false);
		factura.setPrecio(12.20);
		factura.setTipoPago(TipoPago.EFECTIVO);
		facturaService.saveFactura(factura);

	}

	void deleteFacturaTest() throws DataAccessException, TipoPagoException, NoPagadaException {
		cliente = new Cliente();
		cliente.setNombre("Antonio");
		cliente.setApellidos("López");
		cliente.setDni("578624578K");
		cliente.setId(25);
		cliente.setEmail("antoniolopez96@gmail.com");
		cliente.setTelefono("658748325");
		
		factura = new Factura();
		factura.setCliente(cliente);
		factura.setDescripcion("p");
		factura.setFechaEmision(LocalDate.parse("12/8/2020"));
		factura.setId(1);
		factura.setPagado(false);
		factura.setPrecio(12.20);
		factura.setTipoPago(TipoPago.EFECTIVO);
		facturaService.saveFactura(factura);
		
		facturaService.deleteFactura(factura.getId());
		facturaService.findFacturabyFechaEmision(LocalDate.parse("12/8/2020"));
		LocalDate.parse("12/8/2020").equals(factura.getFechaEmision());
	}
}
