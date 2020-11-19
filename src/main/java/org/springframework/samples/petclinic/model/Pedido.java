package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="pedido")
public class Pedido extends BaseEntity{
	
	@Column(name = "fechaEntrada")        
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaEntrada;
	
	@Column(name = "fechaEmision")        
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaEmision;

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}	

}