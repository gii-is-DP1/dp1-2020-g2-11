package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.TipoPago;

public class FacturaServiceTest {
@Autowired FacturaService facturaService;

@Test
@Transactional
void nuevaFacturaTest() {
	Cliente cliente = new Cliente();
	cliente.setNombre("Antonio");
	cliente.setApellidos("López");
	cliente.setDni("578624578K");
	cliente.setId(25);
	cliente.setEmail("antoniolopez96@gmail.com");
	cliente.setTelefono("658748325");
	Factura factura1= new Factura();
	factura1.setCliente(cliente);
	factura1.setDescripcion("p");
	factura1.setHoraEmision(LocalDate.parse("12/8/2020"));
	factura1.setId(1);
	factura1.setPagado(false);
	factura1.setPrecio(12.20);
	factura1.setTipoPago(TipoPago.EFECTIVO);
	facturaService.saveFactura(factura1);
	
}

void deleteFacturaTest() {
	Cliente cliente = new Cliente();
	cliente.setNombre("Antonio");
	cliente.setApellidos("López");
	cliente.setDni("578624578K");
	cliente.setId(25);
	cliente.setEmail("antoniolopez96@gmail.com");
	cliente.setTelefono("658748325");
	Factura factura1= new Factura();
	factura1.setCliente(cliente);
	factura1.setDescripcion("p");
	factura1.setHoraEmision(LocalDate.parse("12/8/2020"));
	factura1.setId(1);
	factura1.setPagado(false);
	factura1.setPrecio(12.20);
	factura1.setTipoPago(TipoPago.EFECTIVO);
	facturaService.saveFactura(factura1);
	facturaService.findFacturabyFechaEmision(LocalDate.parse("12/8/2020"));
	LocalDate.parse("12/8/2020").equals(factura1.getFechaEmision());
}
}
