package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
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
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = VehiculoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class VehiculoControllerTest {

	@Autowired
	private VehiculoController vehiculoController;

	@MockBean
	private ClienteService clienteServices;
	@MockBean
	private VehiculoService vehiculoService;
	@MockBean
	private AuthoritiesService authoritiesServices;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setId(4);
		vehiculo.setFechaFabricacion(LocalDate.of(2017, 05, 28));
		vehiculo.setTipoVehiculo(TipoVehiculo.COCHE);
		vehiculo.setKilometraje(25000);
		vehiculo.setMatricula("2718ODX");
		Collection<Vehiculo> vehiculo1 = new ArrayList<>();
		vehiculo1.add(vehiculo);

//		given(this.vehiculoService.findVehiculos()).willReturn(vehiculo1);
//		given(this.vehiculoService.findVehículoByTipo(TipoVehiculo.COCHE)).willReturn(vehiculo1);
	}

	@WithMockUser(value = "spring")
	@Test
	void findAllVehiculosTest() throws Exception {
		mockMvc.perform(get("/vehiculos")).andExpect(status().isOk()).andExpect(model().attributeExists("vehiculos"))
				.andExpect(view().name("vehiculos/ListaVehiculos"));
	}

//	@WithMockUser(value = "spring")
//	@Test
//	void findVehiculoByMatriculaTest() throws Exception {
//		mockMvc.perform(get("/vehiculo/{vehiculoId}/details", 1)).andExpect(status().isOk())
//				.andExpect(model().attributeExists("vehiculos")).andExpect(view().name("vehiculos/ListaVehiculos"));
//	}

	@WithMockUser(value = "spring")
	@Test
	void findVehiculoByTipoTest() throws Exception {
		mockMvc.perform(get("/vehiculo/tipo")).andExpect(status().isOk())
				.andExpect(view().name("vehiculos/ListaVehiculos"));
	}

	@WithMockUser(value = "spring")
	@Test
	void findMisVehiculosTest() throws Exception {
		mockMvc.perform(get("/vehiculos/cliente")).andExpect(status().isOk())
				.andExpect(view().name("vehiculos/ListaVehiculos"));
	}
}
