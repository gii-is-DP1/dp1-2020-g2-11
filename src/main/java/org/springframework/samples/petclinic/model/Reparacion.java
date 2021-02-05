package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Reparacion")
public class Reparacion extends BaseEntity{
	
	@Column(name = "duracion")
	private Integer duracion;
	
	@Column(name = "precio")
	private Double precio;
	
	@Column(name = "tipoReparacion")
	private TipoReparacion tipoReparacion;
	
	@ManyToOne
	@JoinColumn(name = "vehiculo_id")
	private Vehiculo vehiculo;
	
	@OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
	
}
