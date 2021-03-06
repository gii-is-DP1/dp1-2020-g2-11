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
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.service.CitaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(value = CitaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class CitaControllerTest {

	private static final int TEST_CITA_ID = 1;
	private static final LocalDate TEST_FECHA = LocalDate.of(2020, 1, 25);

	@Autowired
	private CitaController citaController;

	@MockBean
	private CitaService citaService;
	@MockBean
	private ClienteService clienteService;
	@MockBean
	private VehiculoService vehiculoService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		System.out.println("aqui");
		Cita cita = new Cita();
		cita.setId(1);
		cita.setFechaCita(LocalDate.of(2021, 1, 25));
		cita.setHoraCita(LocalTime.of(9, 30));
		Collection<Cita> cita1 = new ArrayList<Cita>();
		cita1.add(cita);

		given(this.citaService.findAllCita()).willReturn(cita1);
		given(this.citaService.findCitaByFechaCita(LocalDate.of(2021, 1, 25))).willReturn(cita1);
	}

	@WithMockUser(value = "spring")
	@Test
	void testCitaList() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/citas/")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("citas"))
				.andExpect(MockMvcResultMatchers.view().name("citas/ListaCitas"));

	}


	@WithMockUser(value = "cliente1")
	@Test
	void testInitCreationFormNotRegistered() throws Exception {
		mockMvc.perform(get("/cita/new")).andExpect(status().isOk())
				.andExpect(view().name("/")).andExpect(model().attributeExists("cita"));
	}

	@WithMockUser(value = "cliente1")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/cita/new").with(csrf())
				.param("fechaCita", "04/05/2021").param("horaCita", "10:00"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(status().isOk());
	}

	// ESCENARIOS NEGATIVOS
	@WithMockUser(value = "cliente1")
	@Test
	void testProcessCreationFormCitaFailed() throws Exception {
		mockMvc.perform(post("/cita/new").with(csrf())
				.param("fechaCita", "")
				.param("horaCita", "18:00"))
				.andExpect(view().name("redirect:/cita/new"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormCitaFailed2() throws Exception {
		mockMvc.perform(post("/cita/new", TEST_CITA_ID).with(csrf())
				.param("fechaCita", "04/05/2021").param("horaCita", ""))
				.andExpect(view().name("redirect:/cita/new"));
	}
}
