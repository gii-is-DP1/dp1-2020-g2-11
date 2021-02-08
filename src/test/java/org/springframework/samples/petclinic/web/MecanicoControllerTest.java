package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Mecanico;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.MecanicoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers=MecanicoController.class,
excludeFilters=@ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class MecanicoControllerTest {
	
	@Autowired
	private MecanicoController mecanicoController;
	
	@MockBean
	private MecanicoService mecanicoService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		User user =new User();
		user.setUsername("anacleto007");
		user.setPassword("00712");
		Mecanico mecanico = new Mecanico();
		mecanico.setApellidos("Perez Perez");
		mecanico.setDni("02644610R");
		mecanico.setEmail("anacleto@gmail.com");
		mecanico.setId(4);
		mecanico.setNombre("Anacleto");
		mecanico.setTelefono("657585793");
		mecanico.setUser(user);
	}
	@WithMockUser(value="spring")
	@Test
	void findAllMecanicosTest() throws Exception{
		mockMvc.perform(get("/mecanicos")).andExpect(status().isOk()).
		andExpect(model().attributeExists("selections")).
		andExpect(view().name("mecanicos/ListaMecanicos"));
	}
}
