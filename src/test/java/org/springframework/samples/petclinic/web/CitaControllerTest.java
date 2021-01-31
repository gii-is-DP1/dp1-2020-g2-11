package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = ProductoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class CitaControllerTest {
	
	@Autowired
	private CitaController citaController;

	@MockBean
	private CitaService citaService;
	
	@Autowired
	private MockMvc mockMvc;
	private static final int TEST_FECHA = LocalDate.of(2021, 1, 25);
	@BeforeEach
	void setup() {
		Cita cita = new Cita();
		cita.setId(1);
		cita.setFechaCita(LocalDate.of(2021, 1, 25));
		cita.setHoraCita(LocalTime.of(9, 30));
		Collection<Cita> cita1 = new ArrayList<Cita>();
		cita1.add(cita);
		
		given(this.citaService.findAllCita()).willReturn(cita1);
		given(this.citaService.findCitaByFechaCita(LocalDate.of(2021, 1, 25)).willReturn(cita1);
		
	}
	
	@WithMockUser(value = "spring")
    @Test
	void findAllCitaTest() throws Exception {
		mockMvc.perform(get("/cita"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("cita"))
		.andExpect(view().name("cita/ListaCitas"));
	}
	
}