package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "vehiculo")
public class Vehiculo extends BaseEntity{
	
	@NotNull
	@Column(name="matricula")
	private String matricula;
	
	@NotNull
	@Column(name="tipoVehiculo")
	private TipoVehiculo tipoVehiculo;
	
	@NotNull
	@Column(name="fechaFabricacion")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate fechaFabricacion;
	
	@NotNull
	@Column (name="kilometraje")
	private Integer kilometraje;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

}
