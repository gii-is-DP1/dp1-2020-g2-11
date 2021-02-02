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
import org.springframework.samples.petclinic.model.Estancia;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.EstanciaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(value = EstanciaController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class EstanciaControllerTest {
	
	@Autowired
	private EstanciaController estanciaController;

	@MockBean
	private EstanciaService estanciaService;
	
	@Autowired
	private MockMvc mockMvc;
	@BeforeEach
	void setup() {
		Estancia estancia = new Estancia();
		estancia.setId(1);
		estancia.setFechaEntrada(LocalDate.of(2020,11, 20));
		estancia.setFechaSalida(LocalDate.of(2020,11, 30));
		estancia.setHoraEntrada(LocalTime.of(10, 30));
		estancia.setHoraSalida(LocalTime.of(12, 30));
		estancia.setDuracion(10);
	}
	@WithMockUser(value = "spring")
	@Test
    void findAllEstancias() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/estancias/")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("selections")).andExpect(MockMvcResultMatchers.view().name("cita/ListaCitas"));
	
	}
	
	 @WithMockUser(value = "spring")
	 @Test
	 void findEstanciasactuales() throws Exception{
		 mockMvc.perform(get("/estanciasactuales")).andExpect(status().isOk())
		 .andExpect(model().attributeExists("estancia2")).andExpect(view().name("estancia/ListaEstancia"));
	 }
	 
	 @WithMockUser(value = "spring")
	 @Test
	 void findEstanciasfechacita() throws Exception{
		 mockMvc.perform(get("/estanciafechacita")).andExpect(status().isOk())
		 .andExpect(model().attributeExists("estancia1")).andExpect(view().name("estancia/ListaEstancia"));
	 }
	
}
