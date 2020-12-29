package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
/////////////////////////////////////
@Entity
@Getter
@Setter
@Table(name = "producto")
public class Producto {
	
	@Id
	@Column(name = "referencia")
	private String referencia;

	@Column(name = "stock")
	private Integer stock;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "marca")
	private String marca;

	@Column(name = "stockSeguridad")
	private Integer stockSeguridad;

}
