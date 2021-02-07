package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
/////////////////////////////////////
@Entity
@Getter
@Setter
@Table(name = "producto")
public class Producto extends BaseEntity{
	
	@javax.validation.constraints.NotBlank
	@Column(name = "referencia")
	private String referencia;

	@NotNull
	@Column(name = "stock")
	private Integer stock;

	@javax.validation.constraints.NotBlank
	@Column(name = "nombre")
	private String nombre;

	@javax.validation.constraints.NotBlank
	@Column(name = "marca")
	private String marca;

	@NotNull
	@Column(name = "stockSeguridad")
	private Integer stockSeguridad;
	
	@Column(name = "disponible")
	private Boolean disponible;

}
