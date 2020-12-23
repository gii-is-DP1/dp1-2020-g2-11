package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cita")
public class Cita extends BaseEntity{

	@Column(name = "fechaCita")        
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaCita;
	
	@Column(name = "horaCita")        
	@DateTimeFormat(pattern = "hh:mm")
	private LocalTime horaCita;

}
