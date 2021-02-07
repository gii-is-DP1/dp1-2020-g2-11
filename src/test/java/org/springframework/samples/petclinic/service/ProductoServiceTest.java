package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class ProductoServiceTest {

	@Autowired
	protected ProductoService productoService;
	private Producto producto;
	
	void newProducto() throws DataAccessException {
	}

	@Test
	@Transactional
	public void shouldInsertProducto() {

		producto = new Producto();
		producto.setNombre("Aceite");
		producto.setMarca("Castrol");
		producto.setReferencia("102375");
		producto.setStock(7);
		producto.setStockSeguridad(3);
		producto.setDisponible(true);

		productoService.saveProducto(producto);
		assertThat(producto.getMarca()).isNotNull();
	}


//	@Test
//	@Transactional
//	public void shouldUpdateProducto() throws DataAccessException, ProductoStockSeguridad {
//
//		producto = new Producto();
//		producto.setNombre("Neumaticos");
//		producto.setMarca("Michelin");
//		producto.setReferencia("95/70R15");
//		producto.setStock(7);
//
//		productoService.saveProducto(producto);
//		producto.setMarca("Nexen");
//		productoService.updateProducto(producto);
//		assertThat(producto.getReferencia()).isNull();
//	}

	@Test
	@Transactional
	public void shoulFindAllProductos() {

		producto = new Producto();
		producto.setNombre("Neumaticos");
		producto.setMarca("Nexen");
		producto.setReferencia("95/70R15");
		producto.setStock(7);
		producto.setStockSeguridad(3);
		producto.setDisponible(true);

		productoService.saveProducto(producto);
		Collection<Producto> productos = (Collection<Producto>) this.productoService.findProductos();
		assertThat(productos.size()).isEqualTo(4);
	}

	@Test
	@Transactional
	public void shoulFindProductoByReferencia() {

		producto = new Producto();
		producto.setNombre("Neumaticos");
		producto.setMarca("Nexen");
		producto.setReferencia("95/70R15");
		producto.setStock(7);
		producto.setStockSeguridad(3);
		producto.setDisponible(false);

		productoService.saveProducto(producto);
		Producto productos = this.productoService.findProductoByReferencia("95/70R15");
		assertThat(productos).isNotNull();
	}

	@Test
	@Transactional
	public void shoulFindProductoByMarca() {

		producto = new Producto();
		producto.setNombre("Neumaticos");
		producto.setMarca("Nexen");
		producto.setReferencia("95/70R15");
		producto.setStock(7);
		producto.setStockSeguridad(3);
		producto.setDisponible(true);

		productoService.saveProducto(producto);
		Collection<Producto> productos = this.productoService.findProductoByMarca("Nexen");
		assertThat(productos.size()).isEqualTo(2);
	}

	@Test
	@Transactional
	public void shoulFindProductoByNombre() {

		producto = new Producto();
		producto.setNombre("Neumaticos");
		producto.setMarca("Nexen");
		producto.setReferencia("95/70R15");
		producto.setStock(7);
		producto.setStockSeguridad(3);
		producto.setDisponible(true);

		productoService.saveProducto(producto);
		Collection<Producto> productos = this.productoService.findProductoByNombre("Neumaticos");
		assertThat(productos.size()).isEqualTo(2);
	}
	
	@Test
	@Transactional
	public void shoulFindProductoById() {
		Producto producto = this.productoService.findProductoById(1);
		assertThat(producto.getNombre().equals("Neumaticos"));
	}
	
	@Test
	@Transactional
	public void shouldFindProductosDisponibles() {
		Collection<Producto> productos = (Collection<Producto>) this.productoService.findProductosDisponibles();
		assertThat(productos.size()).isEqualTo(2);
	}
	
	@Test
	@Transactional
	public void shouldFindProductosNoDisponibles() {
		Collection<Producto> productos = (Collection<Producto>) this.productoService.findProductosNoDisponibles();
		assertThat(productos.size()).isEqualTo(1);
	}
	
	@Test
	@Transactional
	public void shoudOcultarProducto() {
		producto = new Producto();
		producto.setNombre("Neumaticos");
		producto.setMarca("Nexen");
		producto.setReferencia("95/70R15");
		producto.setStock(7);
		producto.setStockSeguridad(3);
		producto.setDisponible(true);

		productoService.saveProducto(producto);
		productoService.ocultarProducto(producto.getId());
		assertThat(producto.getDisponible()).isFalse();
	}
	
	@Test
	@Transactional
	public void shoudDevuelveProducto() {
		producto = new Producto();
		producto.setNombre("Neumaticos");
		producto.setMarca("Nexen");
		producto.setReferencia("95/70R15");
		producto.setStock(7);
		producto.setStockSeguridad(3);
		producto.setDisponible(false);

		productoService.saveProducto(producto);
		productoService.devuelveProducto(producto.getId());
		assertThat(producto.getDisponible()).isTrue();
	}

}
