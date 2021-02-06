package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Reparacion")
public class Reparacion extends BaseEntity{
	
	@NotNull
	@Column(name = "duracion")
	private Integer duracion;
	
	@NotNull
	@Column(name = "precio")
	private Double precio;
	
	@NotNull
	@Column(name = "tipoReparacion")
	private TipoReparacion tipoReparacion;
	
	@ManyToOne
	@JoinColumn(name = "vehiculo_id")
	private Vehiculo vehiculo;
	
	@OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
	
}
