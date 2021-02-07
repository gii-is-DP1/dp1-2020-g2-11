package org.springframework.samples.petclinic.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.samples.petclinic.model.Reparacion;
import org.springframework.samples.petclinic.model.Revision;
import org.springframework.samples.petclinic.model.TipoReparacion;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ReparacionService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ReparacionController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class ReparacionControllerTest {

	@Autowired
	private ReparacionController reparacionController;

	@MockBean
	private ReparacionService reparacionService;
	@MockBean
	private ClienteService clienteService;
	@MockBean
	private VehiculoService vehiculoService;

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		Reparacion reparacion = new Reparacion();
		reparacion.setId(5);
		reparacion.setDuracion(30);
		reparacion.setPrecio(25.00);;
		reparacion.setTipoReparacion(TipoReparacion.MECANICA);

	}
	
	@WithMockUser(value = "spring")
	@Test
	void findAllRevisionesTest() throws Exception {
		mockMvc.perform(get("/reparaciones")).andExpect(status().isOk())
		.andExpect(model().attributeExists("reparaciones"))
		.andExpect(view().name("reparaciones/ListaReparaciones"));
	}
	
	@WithMockUser(value = "spring")
	 @Test
	 void findRevisionById() throws Exception{
		 mockMvc.perform(get("/reparacion/{reparacionId}",1)).andExpect(status().isOk());
		 
	 }
	
	 @WithMockUser(value = "spring")
     @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/reparacion/new")).andExpect(status().isOk())
				.andExpect(view().name("reparaciones/FormularioReparacion")).andExpect(model().attributeExists("reparacion"));
	}
	 
	 @WithMockUser(value = "spring")
     @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/revision/new")
							.with(csrf())
							.param("descripcion", "necesita aceite")
							.param("duracion", "30")
							.param("fechaRevision", "05/02/2021")
							.param("cliente", "62748364G")
							.param("matricula", "2968BPY"))	;
	//			.andExpect(status().is3xxRedirection())
	//			.andExpect(view().name("redirect:/revisiones"));
	}
	 
	 @WithMockUser(value = "spring")
	    @Test
	    void testInitUpdateReparacionForm() throws Exception {
			mockMvc.perform(get("/reparacion/1/edit")).andExpect(status().isOk());
	//		.andExpect(view().name("reparaciones/FormularioReparacion")).andExpect(model().attributeExists("reparacion"));
		}

}
