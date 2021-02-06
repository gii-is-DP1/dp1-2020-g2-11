package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductoValidator implements Validator {
	
	@Override
	public void validate(Object obj, Errors errors) {

		Producto producto = (Producto) obj;
		Integer stock = producto.getStock();
		Integer stockSeguridad = producto.getStockSeguridad();

		if (stock<=stockSeguridad) {
			errors.rejectValue("stockSeguridad", " debe ser menor al stock actual");
		}
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Cita.class.isAssignableFrom(clazz);
	}

}
