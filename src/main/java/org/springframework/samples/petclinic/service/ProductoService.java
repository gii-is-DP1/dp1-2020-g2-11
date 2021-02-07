package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {
	
	private ProductoRepository productoRepository;
	
	@Autowired
	public ProductoService(ProductoRepository ProductoRepository) {
		this.productoRepository = ProductoRepository;
	}
	
	@Transactional
	public void saveProducto(Producto producto) throws DataAccessException {
		producto.setDisponible(true);
		productoRepository.save(producto);
	}
	
//	@Transactional
//	public void deleteProducto(Integer id) throws DataAccessException {
//		productoRepository.deleteById(id);
//	}
	
//	@Transactional
//	public void updateProducto(Producto producto) throws DataAccessException, ProductoStockSeguridad {
//		productoRepository.update(producto);
//		Integer StockSeguridad = ((Producto) productoRepository.findByNombre(producto.getNombre())).getStockseguridad();
//		Integer Stock = ((Producto) productoRepository.findByNombre(producto.getNombre())).getStock();
//		if(Stock <= StockSeguridad) {
//			throw new ProductoStockSeguridad();
//		}
//	}
	
	@Transactional(readOnly = true)	
	public Collection<Producto> findProductos() throws DataAccessException {
		return (Collection<Producto>) productoRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Producto findProductoByReferencia(String referencia) throws DataAccessException {
		return productoRepository.findByReferencia(referencia);
	}
	
	@Transactional(readOnly = true)
	public Collection<Producto> findProductoByMarca(String marca) throws DataAccessException {
		return productoRepository.findByMarca(marca);
	}
	
	@Transactional(readOnly = true)
	public Collection<Producto> findProductoByNombre(String nombre) throws DataAccessException {
		return productoRepository.findByNombre(nombre);
	}

	@Transactional
	public void ocultarProducto(Integer productoId) {
		Producto producto= productoRepository.findById(productoId).get();
		producto.setDisponible(false);
	}
	@Transactional(readOnly = true)
	public Collection<Producto> findProductosDisponibles() {
		return productoRepository.findDisponibles(true);
	}

	@Transactional(readOnly=true)
	public Producto findProductoById(int productoId) {
		return productoRepository.findById(productoId).get();
	}

	
}
