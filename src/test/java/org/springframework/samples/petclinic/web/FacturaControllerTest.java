package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.FacturaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=FacturaController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class FacturaControllerTest {
	
	@Autowired
	private FacturaController facturaController;

	@MockBean
	private FacturaService facturaService;
	private ClienteService clienteService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		Factura factura = new Factura();
		factura.setCliente(clienteService.findClienteById(1).get());
		factura.setDescripcion("factura de un cambio de aceite");
		factura.setFechaEmision(LocalDate.parse("02/01/2021"));
		factura.setId(6);
		factura.setPagado(false);
		factura.setPrecio(20.30);
		factura.setTipoPago(TipoPago.EFECTIVO);
	}
	
	 @WithMockUser(value = "spring")
	 @Test
	 void findAllFacturasTest() throws Exception{
		 mockMvc.perform(get("/facturas")).andExpect(status().isOk())
		 .andExpect(model().attributeExists("selections")).andExpect(view().name("factura/ListaFacturas"));
	 }
	 
	 @WithMockUser(value = "spring")
	 @Test
	 void findFacturaById() throws Exception{
		 mockMvc.perform(get("/facturaId")).andExpect(status().isOk())
		 .andExpect(model().attributeExists("selections")).andExpect(view().name("factura/ListaFacturas"));
	 }
	 
	 @WithMockUser(value = "spring")
	 @Test
	 void findFacturaByFechaEmision() throws Exception{
		 mockMvc.perform(get("/facturaFechaEmision")).andExpect(status().isOk())
		 .andExpect(model().attributeExists("selections")).andExpect(view().name("factura/ListaFacturas"));
	 }
	 
	 @WithMockUser(value = "spring")
	 @Test
	 void findFacturaPagado() throws Exception{
		 mockMvc.perform(get("/facturaPago")).andExpect(status().isOk())
		 .andExpect(model().attributeExists("selections")).andExpect(view().name("factura/ListaFacturas"));
	 }
}
