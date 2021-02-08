package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ProductoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class ProductoControllerTest {

	@Autowired
	private ProductoController productoController;

	@MockBean
	private ProductoService productoService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Producto producto = new Producto();
		producto.setId(5);
		producto.setReferencia("95/65R15");
		producto.setNombre("Neumatico");
		producto.setMarca("Firestone");
		producto.setStock(4);
		producto.setStockSeguridad(2);
		producto.setDisponible(true);

		given(this.productoService.findProductoByReferencia("NEU54638")).willReturn(new Producto());

	}

	@WithMockUser(value = "spring")
	@Test
	void findAllProductosTest() throws Exception {
		mockMvc.perform(get("/productos")).andExpect(status().isOk())
				.andExpect(model().attributeExists("producto"))
				.andExpect(view().name("productos/ListaProductos"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void findAllProductosNoDisponiblesTest() throws Exception {
		mockMvc.perform(get("/productos/productosNoDisponibles")).andExpect(status().isOk())
				.andExpect(model().attributeExists("producto"))
				.andExpect(view().name("productos/ProductosNoDisponibles"));
	}

//	@WithMockUser(value = "spring")
//	@Test
//	void findProductoByNombreTest() throws Exception {
//		mockMvc.perform(get("/productosbynombre")).andExpect(status().isOk())
//				.andExpect(view().name("productos/ListaProductos"));
//		// mockMvc.perform(get("/productos/{productoId}",1)).andExpect(status().isOk());
//	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/productos/new")).andExpect(status().isOk())
				.andExpect(view().name("productos/FormularioProducto"))
				.andExpect(model().attributeExists("producto"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/productos/new").with(csrf())
				// .param("id", "1")
				.param("referencia", "NEU54638")
				.param("nombre", "Neumaticos")
				.param("marca", "Nexen")
				.param("stock", "10")
				.param("stockSeguridad", "4"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/productos"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/productos/new").with(csrf())
				.param("referencia", "NEU54638")
				.param("nombre", "Neumaticos")
				.param("marca", "Nexen")
				.param("stock", "3")
				.param("stockSeguridad", "4"))
				.andExpect(model().attributeHasErrors("producto"))
				.andExpect(model().attributeHasFieldErrors("producto", "stock")).andExpect(status().isOk())
				.andExpect(view().name("productos/FormularioProducto"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void ocultarProductoTest() throws Exception {
		mockMvc.perform(get("/productos/oculta/{productoId}",1)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/productos"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void devuelveProductoTest() throws Exception {
		mockMvc.perform(get("/productos/devuelve/{productoId}",1)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/productos"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessInitUpdateForm() throws Exception {
		mockMvc.perform(get("/productos/{productoId}/edit",1)).andExpect(status().isOk())
				.andExpect(view().name("productos/FormularioProducto"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		mockMvc.perform(post("/productos/{productoId}/edit",1).with(csrf())
				// .param("id", "1")
				.param("referencia", "NEU54638")
				.param("nombre", "Neumaticos")
				.param("marca", "Nexen")
				.param("stock", "7")
				.param("stockSeguridad", "4"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/productos"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormHasErrors() throws Exception {
		mockMvc.perform(post("/productos/{productoId}/edit",1).with(csrf())
				.param("referencia", "")
				.param("nombre", "Neumaticos")
				.param("marca", "Nexen")
				.param("stock", "7")
				.param("stockSeguridad", "4"))
				.andExpect(model().attributeHasErrors("producto"))
				.andExpect(model().attributeHasFieldErrors("producto", "referencia")).andExpect(status().isOk())
				.andExpect(view().name("productos/FormularioProducto"));
	}

}
