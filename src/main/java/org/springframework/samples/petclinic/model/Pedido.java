package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Pedido extends BaseEntity {
	
	@Column(name = "id_pedido")
	@NotEmpty
	protected Integer id_pedido;
	
	@Column(name = "fecha_entrada")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fecha_entrada;
	
	@Column(name = "fecha_salida")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fecha_salida;
	
	@Column(name = "precio")        
	@NotEmpty
	protected Double precio;

	public Integer getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}

	public LocalDate getFecha_entrada() {
		return fecha_entrada;
	}

	public void setFecha_entrada(LocalDate fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	public LocalDate getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(LocalDate fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}
