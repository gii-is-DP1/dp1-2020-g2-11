package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "proveedor")
public class Proveedor extends BaseEntity {
	
	@javax.validation.constraints.NotBlank
	@Column(name = "nombre")
	private String nombre;

	@javax.validation.constraints.NotBlank
	@Column(name = "telefono")
	private String telefono;

	@javax.validation.constraints.NotBlank
	@Column(name = "direccion")
	private String direccion;

	@javax.validation.constraints.NotBlank
	@Column(name = "email")
	private String email;
	
	@Column(name = "disponible")
	private Boolean disponible;
	
}
