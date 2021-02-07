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
		Proveedor proveedores = this.proveedorService.findProveedorByNombre("Neumaticos Paco");
		assertThat(proveedores).isNotNull();
	}
	
	@Test
	@Transactional
	public void shoulFindAllProveedores() {
		Collection<Proveedor> proveedores = this.proveedorService.findProveedores();
		assertThat(proveedores.size()).isEqualTo(2);
	}
	
	@Test
	@Transactional
	public void shoulDeleteProveedores() {
		proveedorService.deleteProveedor(2);
        Collection<Proveedor> proveedores = this.proveedorService.findProveedores();
        assertThat(proveedores.size()).isEqualTo(1);
		
	}

}
