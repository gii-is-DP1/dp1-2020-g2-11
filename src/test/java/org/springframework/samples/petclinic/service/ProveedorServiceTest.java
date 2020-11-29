package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;
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
	
	void newProveedor() throws DataAccessException{
		
	}
	
	@Test
	@Transactional
	public void shouldInsertProveedor() {

		Proveedor proveedor = new Proveedor();
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

		Proveedor proveedor1 = new Proveedor();
		proveedor1.setNombre("Aceites Juan");
		proveedor1.setTelefono("655212326");
		proveedor1.setDireccion("C/Jacinto");
		proveedor1.setEmail("aceitesjuan@gmail.com");         
                
		proveedorService.saveProveedor(proveedor1);
		Collection<Proveedor> proveedores = this.proveedorService.findProveedorByNombre("Aceites Juan");
		assertThat(proveedores.size()).isEqualTo(1);

	}
	
	@Test
	@Transactional
	public void shoulFindAllProveedores() {

		Proveedor proveedor2 = new Proveedor();
		proveedor2.setNombre("Aceites Pedro");
		proveedor2.setTelefono("655442326");
		proveedor2.setDireccion("C/Guadalquivie");
		proveedor2.setEmail("aceitespedro@gmail.com");         
                
		proveedorService.saveProveedor(proveedor2);
		Collection<Proveedor> proveedores = this.proveedorService.findProveedorByNombre("Aceites Juan");
		assertThat(proveedores.size()).isEqualTo(2);
	}
	
	@Test
	@Transactional
	public void shoulDeleteProveedores() {

		Proveedor proveedor2 = new Proveedor();
		proveedor2.setNombre("Neumaticos Paco");
		proveedor2.setTelefono("655442233");
		proveedor2.setDireccion("C/Reina Mercedes");
		proveedor2.setEmail("neumaticosPaco@gmail.com");         
                
		proveedorService.saveProveedor(proveedor2);
		proveedorService.deleteProveedor("Neumaticos Paco");
		assertThat(proveedorService.findProveedorByNombre("Neumaticos Paco")).isNull();
		
	}
	
	
	
	

}
