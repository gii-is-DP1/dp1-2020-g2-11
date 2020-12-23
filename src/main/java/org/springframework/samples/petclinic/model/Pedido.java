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
@Table(name="pedido")
public class Pedido extends BaseEntity{
	
	@Column(name = "fechaEntrada")        
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaEntrada;
	
	@Column(name = "fechaEmision")        
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaEmision;

}
