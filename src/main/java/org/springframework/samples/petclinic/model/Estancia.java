package org.springframework.samples.petclinic.model;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "estancia")
public class Estancia extends BaseEntity {

	@Column(name = "fechaEntrada")        
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private LocalDateTime fechaEntrada;
	
	@Column(name = "fechaSalida")        
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private LocalDateTime fechaSalida;
	
	@Column(name = "duracion")        
	private Integer duracion;

	public LocalDateTime getFechaEntrada() {
		return fechaEntrada.withSecond(0);
	}

	public void setFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada.withSecond(0);
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida.withSecond(0);
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida.withSecond(0);
	}

	public Integer getDuracion() {
		return (int) Duration.between(this.fechaEntrada, this.fechaSalida).toHours();
	}
	
}
