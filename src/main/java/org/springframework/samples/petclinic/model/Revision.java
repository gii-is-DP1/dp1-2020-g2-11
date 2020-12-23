package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "revision")
public class Revision extends BaseEntity {

	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "duracion")
	private Integer duracion;
	@Column(name = "fechaRevision")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaRevision;

}
