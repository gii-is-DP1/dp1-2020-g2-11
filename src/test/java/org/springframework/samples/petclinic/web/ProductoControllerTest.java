package org.springframework.samples.petclinic.web;

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

@WebMvcTest(controllers=ProductoController.class,
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
		producto.setId(1);
		producto.setReferencia("95/65R15");
		producto.setNombre("Neumatico");
		producto.setMarca("Firestone");
		producto.setStock(4);
		producto.setStockSeguridad(2);
		
		//given(this.productoService.findProductos()).willReturn(Lists.newArrayList(producto));
		
	}
	
	 @WithMockUser(value = "spring")
	 @Test
	 void findAllProductosTest() throws Exception{
		 mockMvc.perform(get("/productos")).andExpect(status().isOk())
		 .andExpect(model().attributeExists("selections")).andExpect(view().name("producto/ListaProductos"));
	 }
	 
	 @WithMockUser(value = "spring")
	 @Test
	 void findProductoByNombreTest() throws Exception{
		 mockMvc.perform(get("/productonombre")).andExpect(status().isOk())
		 .andExpect(model().attributeExists("producto")).andExpect(view().name("producto/ListaProductos"));
		// mockMvc.perform(get("/productos/{productoId}",1)).andExpect(status().isOk());
	 }
	 
	 @WithMockUser(value = "spring")
     @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/producto/new")).andExpect(status().isOk())
				.andExpect(view().name("productos/FormularioProducto")).andExpect(model().attributeExists("producto"));
	}

//	@WithMockUser(value = "spring")
//     @Test
//	void testProcessCreationFormSuccess() throws Exception {
//		mockMvc.perform(post("producto/new")
//							.with(csrf())
//							.param("referencia", "95/65R15")
//							.param("nombre", "Neumatico")
//							.param("marca", "Firestone")
//							.param("stock", "4")
//							.param("stockSeguridad", "2"))		 	
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/productos/ListaProducto"));
//	}

//	@WithMockUser(value = "spring")
// @Test
//	void testProcessCreationFormHasErrors() throws Exception {
//		mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit", TEST_OWNER_ID, TEST_PET_ID)
//							.with(csrf())
//							.param("name", "Betty")
//							.param("birthDate", "2015/02/12"))
//				.andExpect(model().attributeHasNoErrors("owner"))
//				.andExpect(model().attributeHasErrors("pet"))
//				.andExpect(status().isOk())
//				.andExpect(view().name("pets/createOrUpdatePetForm"));
//	}

}
