//package org.springframework.samples.petclinic.web;
//
//import static org.mockito.BDDMockito.given;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Collection;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
//import org.springframework.samples.petclinic.service.PedidosService;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@WebMvcTest(value = PedidoController.class,
//excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
//excludeAutoConfiguration= SecurityConfiguration.class)
//public class PedidoControllerTest {
//	
//	@Autowired
//	private PedidoController pedidoController;
//	
//	@MockBean
//	private PedidosService pedidoService;
//	
//	@Autowired
//	private MockMvc mockMvc;
//	private static final LocalDate TEST_FECHA = LocalDate.of(2020, 1, 25);
//	
//	@BeforeEach
//	void setup() {
//		
//	}
//}