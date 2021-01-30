package org.springframework.samples.petclinic.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
	
	
	@Column(name= "horaEntrada")
	@DateTimeFormat(pattern= "hh:mm")
	private LocalTime horaEntrada;
	
	
	@Column(name = "fechaSalida")        
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaSalida;
	
	
	@Column(name= "horaSalida")
	@DateTimeFormat(pattern= "hh:mm")
	private LocalTime horaSalida;
	
	
	@Column(name = "duracion")        
	private Integer duracion;

	
	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	
	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}
	
	
	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	
	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada=horaEntrada;
	}
	
	
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida=horaSalida;
	}
	public Integer getDuracion() {
		return (int) Duration.between(this.fechaEntrada, this.fechaSalida).toHours();
	}
	
}
