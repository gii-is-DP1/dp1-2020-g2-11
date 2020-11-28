package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.transaction.annotation.Transactional;

class ProductoServiceTest {
	
	protected ProductoService productoService;

	@Test
	@Transactional
	public void shouldInsertProducto() {

		Producto producto = new Producto();
		producto.setNombre("Aceite");
		producto.setMarca("Castrol");
		producto.setReferencia("102375");
		producto.setStock(7);
		producto.setStockseguridad(3);                
                
		productoService.saveProducto(producto);
		assertThat(producto.getMarca()).isNotNull();

	}

}
