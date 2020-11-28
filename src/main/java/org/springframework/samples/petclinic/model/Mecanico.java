package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mecanico")
public class Mecanico extends Person {
	
	@Column(name = "anyosExperiencia")
	private Integer anyosExperiencia;

	
}
