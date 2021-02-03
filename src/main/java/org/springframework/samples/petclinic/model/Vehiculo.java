package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
	@DateTimeFormat(pattern="dd/MM/YYYY")
	private LocalDate fechaFabricacion;
	
	@Column (name="kilometraje")
	private Integer kilometraje;
	
//	@OneToMany(mappedBy ="vehiculo")
//	private List<Estancia> estancias;

}
