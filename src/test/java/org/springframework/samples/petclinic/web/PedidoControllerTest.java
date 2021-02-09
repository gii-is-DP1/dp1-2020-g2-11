package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(value = PedidoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class PedidoControllerTest {
	
	@Autowired
	private PedidoController pedidoController;
	
	@MockBean
	private PedidoService pedidoService;
	@MockBean
	private ProveedorService proveedorService;
	@MockBean
	private ProductoService productoService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		Pedido pedido = new Pedido();
		pedido.setId(5);
		pedido.setFechaEntrada(LocalDate.of(2021, 2, 5));
		pedido.setFechaEmision(LocalDate.of(2021, 2, 4));
		pedido.setProducto(productoService.findProductoById(1));
		pedido.setProveedor(proveedorService.findProveedorById(1));	
	}
	
	@WithMockUser(value = "spring")
	@Test
	void findAllPedidosTest() throws Exception {
		mockMvc.perform(get("/pedidos")).andExpect(status().isOk())
				.andExpect(model().attributeExists("pedidos"))
				.andExpect(view().name("pedidos/ListaPedidos"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/pedido/new")).andExpect(status().isOk())
				.andExpect(view().name("pedidos/FormularioPedido"))
				.andExpect(model().attributeExists("pedido"));
	}
	//ESCENARIO POSITIVO
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/pedido/new").with(csrf())
				// .param("id", "1")
				.param("fechaEntrada", "14/12/2020")
				.param("fechaEmision", "10/12/2020")
				.param("proveedor.id", "1")
				.param("producto.nombre", "Neumaticos"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/pedidos"));
		
	}
	//ESCENARIO NEGATIVO
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormFailed() throws Exception {
		mockMvc.perform(post("/pedido/new").with(csrf())
				// .param("id", "1")
				.param("fechaEntrada", "14/12/2020")
				.param("fechaEmision", "")
				.param("proveedor.id", "1")
				.param("producto.nombre", "Neumaticos"))
			.andExpect(model().attributeHasErrors("pedido"))
			.andExpect(model().attributeHasFieldErrors("pedido", "fechaEmision")).andExpect(status().isOk())
		.andExpect(view().name("pedidos/FormularioPedido"));
		
	}
}