package org.springframework.samples.petclinic.model;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

	/**
	 * Simple JavaBean domain object representing an person.
	 *
	 * @author Ken Krebs
	 */
	@MappedSuperclass
	@Getter
	@Setter
	@Table(name = "Persona")
	public class Person extends BaseEntity {

		@Column(name = "nombre")
		@NotEmpty
		protected String nombre;

		@Column(name = "apellidos")
		@NotEmpty
		protected String apellidos;
		
		@Pattern(regexp = "\\d{8}[A-HJ-NP-TV-Z]")
		@Column(name =  "dni", unique=true)
		@NotEmpty
		private String dni;
		
		@Column(name = "telefono")
		private String telefono;

		@Column(name = "email")
		private String email;
		

		

}

