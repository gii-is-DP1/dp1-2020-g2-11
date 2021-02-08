package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "vehiculo")
public class Vehiculo extends BaseEntity{
	
	@Column(name="matricula")
	private String matricula;
	
	@Column(name="tipoVehiculo")
	private TipoVehiculo tipoVehiculo;
	
	@Column(name="fechaFabricacion")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate fechaFabricacion;
	
	@Column (name="kilometraje")
	private Integer kilometraje;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

}
