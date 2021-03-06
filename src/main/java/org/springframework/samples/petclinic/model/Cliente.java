package org.springframework.samples.petclinic.model;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cliente")
public class Cliente extends Person {
	
	@OneToMany(mappedBy ="cliente")
	private List<Factura> facturas;
	
	@OneToMany(mappedBy ="cliente")
	private List<Vehiculo> vehiculos;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;

}
