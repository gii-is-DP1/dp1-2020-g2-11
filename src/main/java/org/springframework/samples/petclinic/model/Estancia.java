package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estancia")
public class Estancia extends BaseEntity {

	@NotNull
	@Column(name = "fechaEntrada")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaEntrada;

	@NotNull
	@Column(name = "horaEntrada")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horaEntrada;

	@NotNull
	@Column(name = "fechaSalida")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaSalida;

	@NotNull
	@Column(name = "horaSalida")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horaSalida;

	@NotNull
	@Column(name = "duracion")
	private Integer duracion;
	
	@ManyToOne
	@JoinColumn(name="vehiculo_id")
	private Vehiculo vehiculo;
}
