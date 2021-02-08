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
import org.springframework.samples.petclinic.model.Revision;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.MecanicoService;
import org.springframework.samples.petclinic.service.RevisionService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = RevisionController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class RevisionControllerTest {

	@Autowired
	private RevisionController revisionController;

	@MockBean
	private RevisionService revisionService;
	@MockBean
	private ClienteService clienteService;
	@MockBean
	private VehiculoService vehiculoService;
	@MockBean
	private MecanicoService mecanicoService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Revision revision = new Revision();
		revision.setId(5);
		revision.setDescripcion("Ruedas traseras y frenos desgastados");
		revision.setDuracion(5);
		revision.setFechaRevision(LocalDate.of(2021, 1, 23));
	}

	@WithMockUser(value = "spring")
	@Test
	void findAllRevisionesTest() throws Exception {
		mockMvc.perform(get("/revisiones")).andExpect(status().isOk())
		.andExpect(model().attributeExists("revisiones"))
		.andExpect(view().name("revisiones/ListaRevisiones"));
	}

	@WithMockUser(value = "spring")
	@Test
	void findRevisionByFechaTest() throws Exception {
		mockMvc.perform(get("/revisionFecha")).andExpect(status().isOk())
			.andExpect(model().attributeExists("revisiones"))
			.andExpect(view().name("revisiones/ListaRevisiones"));
	}
	
	 @WithMockUser(value = "spring")
	 @Test
	 void findRevisionById() throws Exception{
		 mockMvc.perform(get("/revision/{revisionId}",1)).andExpect(status().isOk());
		 
	 }
	 
	 @WithMockUser(value = "spring")
     @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/revision/new")).andExpect(status().isOk())
				.andExpect(view().name("revisiones/FormularioRevision")).andExpect(model().attributeExists("revision"));
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
							.param("matricula", "2968BPY")).andExpect(status().isOk());	
				//.andExpect(status().is3xxRedirection())
				//.andExpect(view().name("redirect:/revisiones"));
	}
	 
	 
}