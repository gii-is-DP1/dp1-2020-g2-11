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
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = ClienteController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class ClienteControllerTest {

	@Autowired
	private ClienteController clienteController;

	@MockBean
	private ClienteService clienteService;
	@MockBean
	private VehiculoService vehiculoService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Cliente cliente = new Cliente();
		User user = new User();
		user.setUsername("bombi547");
		user.setPassword("1975bebe");
		cliente.setNombre("Perez Perez");
		cliente.setApellidos("02644610R");
		cliente.setDni("anacleto@gmail.com");
		cliente.setId(4);
		cliente.setTelefono("Anacleto");
		cliente.setEmail("657585793");
		cliente.setUser(user);
	}
	
	
	
	 @WithMockUser(value = "spring")
	 @Test
	 void findAllClientesTest() throws Exception{
		 mockMvc.perform(get("/clientes")).andExpect(status().isOk())
		 .andExpect(model().attributeExists("cliente")).andExpect(view().name("cliente/ListaClientes"));
	 }
	 	 
	 @WithMockUser(value = "spring")
     @Test
	void testInitFindForm() throws Exception {
		mockMvc.perform(get("/cliente/find")).andExpect(status().isOk())
				.andExpect(view().name("cliente/findClientes")).andExpect(model().attributeExists("cliente"));
	
	 }
	 
	 @WithMockUser(value = "spring")
	 @Test
		void testProcessFindFormSuccess() throws Exception {
			mockMvc.perform(get("/cliente")
								.with(csrf()))
					.andExpect(status().is3xxRedirection())
					.andExpect(view().name("redirect:/cliente/find"));
		}
	 
		@WithMockUser(value = "spring")
		@Test
		void testInitEditCliente() throws Exception {
			mockMvc.perform(get("/cliente/{clienteId}/edit", 1))
			.andExpect(status().isOk());  
//			.andExpect(model().attributeExists("cliente"))
//			.andExpect(view().name("redirect:/cliente/ListaClientes"));
		}
		
		@WithMockUser(value = "spring")
		@Test
		void testEditClienteSuccess() throws Exception {
			mockMvc.perform(post("/cliente/{clienteId}/edit", 1)
				.with(csrf())
				.param("nombre", "Marcos")
				.param("apellidos", "Viera Rodriguez")
				.param("dni", "62748364G")
				.param("telefono", "633572849")
				.param("email", "manuvierod@gmail.com"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/cliente/{clienteId}"));
				
			}
	 
	 
		 
}