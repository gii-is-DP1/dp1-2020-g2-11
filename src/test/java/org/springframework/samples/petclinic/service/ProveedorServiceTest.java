package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ProveedorServiceTest {
	
	@Autowired
	protected ProveedorService proveedorService;
	private Proveedor proveedor;
	void newProveedor() throws DataAccessException{
	}
	
	@Test
	@Transactional
	public void shouldInsertProveedor() {
		proveedor = new Proveedor();
		proveedor.setNombre("Aceites Juan");
		proveedor.setTelefono("655212326");
		proveedor.setDireccion("C/Jacinto");
		proveedor.setEmail("aceitesjuan@gmail.com");
                
		proveedorService.saveProveedor(proveedor);
		assertThat(proveedor.getNombre()).isNotNull();
	}
	
	@Test
	@Transactional
	public void shoulFindProveedorByNombre() {
		proveedor = new Proveedor();
		proveedor.setNombre("Aceites Juan");
		proveedor.setTelefono("655212326");
		proveedor.setDireccion("C/Jacinto");
		proveedor.setEmail("aceitesjuan@gmail.com");
                
		proveedorService.saveProveedor(proveedor);
		Proveedor proveedores = this.proveedorService.findProveedorByNombre("Aceites Juan");
		assertThat(proveedores).isNotNull();
	}
	
	@Test
	@Transactional
	public void shoulFindAllProveedores() {
		proveedor = new Proveedor();
		proveedor.setNombre("Aceites Juan");
		proveedor.setTelefono("655212326");
		proveedor.setDireccion("C/Jacinto");
		proveedor.setEmail("aceitesjuan@gmail.com");
                
		proveedorService.saveProveedor(proveedor);
		Collection<Proveedor> proveedores = this.proveedorService.findProveedores();
		assertThat(proveedores.size()).isEqualTo(9);
	}
	
	@Test
	@Transactional
	public void shoulFindProveedoresById() {
		proveedor = new Proveedor();
		proveedor.setId(8);
		proveedor.setNombre("Aceites Juan");
		proveedor.setTelefono("655212326");
		proveedor.setDireccion("C/Jacinto");
		proveedor.setEmail("aceitesjuan@gmail.com");
                
		proveedorService.saveProveedor(proveedor);
		Proveedor proveedores = this.proveedorService.findProveedorById(8);
		assertThat(proveedores).isNotNull();
	}
	@Test
	@Transactional
	public void shoulFindProveedoresByDisponibles() {
		proveedor = new Proveedor();
		proveedor.setId(8);
		proveedor.setNombre("Aceites Juan");
		proveedor.setTelefono("655212326");
		proveedor.setDireccion("C/Jacinto");
		proveedor.setEmail("aceitesjuan@gmail.com");
		proveedor.setDisponible(true);
		Collection<Proveedor> proveedores = this.proveedorService.findProveedoresDisponibles();
		assertThat(proveedores).isNotNull();

	}
	@Test
	@Transactional
	public void shoulFindProveedoresByNoDisponibles() {
		proveedor = new Proveedor();
		proveedor.setId(8);
		proveedor.setNombre("Aceites Juan");
		proveedor.setTelefono("655212326");
		proveedor.setDireccion("C/Jacinto");
		proveedor.setEmail("aceitesjuan@gmail.com");
		proveedor.setDisponible(false);
		Collection<Proveedor> proveedores = this.proveedorService.findProveedoresNoDisponibles();
		assertThat(proveedores).isNotNull();

	}
	@Test
	@Transactional
	public void shouldOcultProveedores() {
		proveedor = new Proveedor();
		proveedor.setId(8);
		proveedor.setNombre("Aceites Juan");
		proveedor.setTelefono("655212326");
		proveedor.setDireccion("C/Jacinto");
		proveedor.setEmail("aceitesjuan@gmail.com");
		proveedor.setDisponible(true);
		proveedorService.saveProveedor(proveedor);
		this.proveedorService.ocultaProveedorDisponible(8);
		Proveedor proveedores = this.proveedorService.findProveedorById(8);
		assertThat(proveedores.getDisponible()).isEqualTo(false);

	}
	@Test
	@Transactional
	public void shouldShowProveedores() {
		proveedor = new Proveedor();
		proveedor.setId(8);
		proveedor.setNombre("Aceites Juan");
		proveedor.setTelefono("655212326");
		proveedor.setDireccion("C/Jacinto");
		proveedor.setEmail("aceitesjuan@gmail.com");
		proveedor.setDisponible(false);
		proveedorService.saveProveedor(proveedor);
		this.proveedorService.devuelveProveedorNoDisponible(8);
		Proveedor proveedores = this.proveedorService.findProveedorById(8);
		assertThat(proveedores.getDisponible()).isEqualTo(true);

	}
	}
