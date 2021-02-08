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
@Table(name = "factura")
public class Factura extends BaseEntity {
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@NotNull
	@Column(name = "precio")
	private Double precio;
	@NotNull
	@Column(name = "totalprecio")
	private Double totalprecio;
	
	@NotNull
	@Column(name = "tipoPago")
	private TipoPago tipoPago;
	
	@NotNull
	@Column(name = "fechaEmision")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaEmision;
	
	@NotNull
	@Column(name = "pagado")
	private Boolean pagado;
	

	@ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
