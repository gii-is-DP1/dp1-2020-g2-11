package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

	@Column(name = "referencia")
	private String referencia;

	@Column(name = "stock")
	private Integer stock;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "marca")
	private String marca;

	@Column(name = "stockseguridad")
	private Integer stockseguridad;

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getStockseguridad() {
		return stockseguridad;
	}

	public void setStockseguridad(Integer stockseguridad) {
		this.stockseguridad = stockseguridad;
	}

}
