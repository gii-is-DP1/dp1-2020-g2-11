package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.assertj.core.util.Lists;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(value = ProductoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
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
		
	}
	
	 @WithMockUser(value = "spring")
	 @Test
	 void findAllProductosTest() throws Exception{
		 mockMvc.perform(get("/producto")).andExpect(status().isOk())
		 .andExpect(view().name("producto/ListaProductos"));
	 }
	 
	 @WithMockUser(value = "spring")
	 @Test
	 void findProductoByNombreTest() throws Exception{
		 mockMvc.perform(get("/productos/{productoId}",1)).andExpect(status().isOk());
	 }

}
