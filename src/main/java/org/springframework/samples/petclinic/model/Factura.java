package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "factura")
public class Factura extends BaseEntity {
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "precio")
	private Double precio;
	
	@Column(name = "tipoPago")
	private TipoPago tipoPago;
	
	@Column(name = "fechaEmision")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate horaEmision;
	
	@Column(name = "pagado")
	private Boolean pagado;

	public Boolean getPagado() {
		return pagado;
	}

	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public TipoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public LocalDate getHoraEmision() {
		return horaEmision;
	}

	public void setHoraEmision(LocalDate horaEmision) {
		this.horaEmision = horaEmision;
	}
	
	

}
