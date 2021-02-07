package org.springframework.samples.petclinic.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = ProveedorController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ProveedorControllerTest {
		
	@Autowired
	private ProveedorController proveedorController;
	
	@MockBean
	private ProveedorService proveedorService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		Proveedor proveedor = new Proveedor();
		proveedor.setId(5);
		proveedor.setNombre("Marcos");
		proveedor.setTelefono(654857147);
		proveedor.setDireccion("Virgen de las Angustias, 9");
		proveedor.setEmail("marquitosbambino@gmail.com");
	}
	
	 @WithMockUser(value = "spring")
	 @Test
	 void findAllProveedoresTest() throws Exception{
		 mockMvc.perform(get("/proveedores")).andExpect(status().isOk())
		 .andExpect(model().attributeExists("proveedor")).andExpect(view().name("proveedores/ListaProveedores"));
	 }
	 
	 @WithMockUser(value = "spring")
	 @Test
	 void findProveedorByNombreTest() throws Exception{
		 mockMvc.perform(get("/proveedorbynombre")).andExpect(status().isOk())
		 .andExpect(model().attributeExists("proveedor")).andExpect(view().name("proveedores/ListaProveedores"));
		// mockMvc.perform(get("/productos/{productoId}",1)).andExpect(status().isOk());
	}

	 @WithMockUser(value = "spring")
     @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/proveedores/new")).andExpect(status().isOk())
				.andExpect(view().name("proveedores/FormularioProveedor")).andExpect(model().attributeExists("proveedor"));
	}

	@WithMockUser(value = "spring")
     @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("proveedores/new")
							.with(csrf())
							//.param("id", "1")
							.param("nombre", "Neumaticos Paco")
							.param("telefono", "653746489")
							.param("direccion", "c/Ave del Paraiso n31")
							.param("email", "neumaticospaco@gmail.com")).andExpect(status().is4xxClientError());
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("proveedores/ListaProveedores"));
	}
 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}