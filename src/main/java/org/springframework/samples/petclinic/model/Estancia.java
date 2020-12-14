package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "estancia")
public class Estancia extends BaseEntity {

	@Column(name = "fechaEntrada")        
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaEntrada;
	
	@Column(name = "fechaSalida")        
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaSalida;
	
	@Column(name = "duracion")        
	private Integer duracion;

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	
}
