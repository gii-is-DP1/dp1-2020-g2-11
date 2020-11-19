package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import javax.persistence.Column;
import org.springframework.format.annotation.DateTimeFormat;

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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public LocalDate getFechaFabricacion() {
		return fechaFabricacion;
	}

	public void setFechaFabricacion(LocalDate fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	public Integer getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(Integer kilometraje) {
		this.kilometraje = kilometraje;
	}
	
}