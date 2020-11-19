package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Reparacion")
public class Reparacion extends BaseEntity{
	
	@Column(name = "duracion")
	private Integer duracion;
	@Column(name = "precio")
	private Integer precio;
	@Column(name = "tipoReparacion")
	private TipoReparacion tipoReparacion;
	
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	public TipoReparacion getTipoReparacion() {
		return tipoReparacion;
	}
	public void setTipoReparacion(TipoReparacion tipoReparacion) {
		this.tipoReparacion = tipoReparacion;
	}
	
	
	
	

}
