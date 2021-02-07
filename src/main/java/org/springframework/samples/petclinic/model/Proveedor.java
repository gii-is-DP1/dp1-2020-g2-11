package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "proveedor")
public class Proveedor extends BaseEntity {
	

	@javax.validation.constraints.NotBlank
	@NotEmpty
	@Column(name = "nombre")
	private String nombre;

	@NotNull
	@Column(name = "telefono")
	private Integer telefono;

	@javax.validation.constraints.NotBlank
	@NotEmpty
	@Column(name = "direccion")
	private String direccion;

	@javax.validation.constraints.NotBlank
	@NotEmpty
	@Column(name = "email")
	private String email;
	
	@Column(name = "disponible")
	private Boolean disponible;
	
}
