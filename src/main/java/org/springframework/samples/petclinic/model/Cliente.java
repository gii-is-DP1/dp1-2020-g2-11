/*
<<<<<<< HEAD
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple business object representing a pet.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 */
@Entity
@Getter
@Setter
@Table(name = "cliente")
public class Cliente extends Person {
	
	@OneToMany(mappedBy ="cliente")
	private List<Factura> facturas;
	
//	@Column(name ="factura")
//	private Factura factura;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
//	public Factura getFacturas(Integer idFactura) {
//		for(int i=0;i<facturas.size();i++) {
//			if(idFactura == facturas.get(i).getId()) {
//				this.factura = facturas.get(i);
//			}
//		}		
//		return factura;
//	}	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	private Set<Vehiculo> vehiculos;
	
	protected Set<Vehiculo> getVehiculoInternal() {
		if (this.vehiculos == null) {
			this.vehiculos = new HashSet<>();
		}
		return this.vehiculos;
	}

	protected void setVehiculoInternal(Set<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public List<Vehiculo> getVehiculos() {
		List<Vehiculo> sortedVehiculos = new ArrayList<>(getVehiculoInternal());
		PropertyComparator.sort(sortedVehiculos, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedVehiculos);
	}

	public void addVehiculo(Vehiculo vehiculo) {
		getVehiculoInternal().add(vehiculo);
		vehiculo.setCliente(this);
	}
	
	public boolean removeVehiculo(Vehiculo vehiculo) {
		return getVehiculoInternal().remove(vehiculo);
	}
}
