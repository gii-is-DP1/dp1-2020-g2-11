package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Producto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductoValidator implements Validator {
	
	@Override
	public void validate(Object obj, Errors errors) {

		Producto producto = (Producto) obj;
		String nombre=producto.getNombre();
		String marca = producto.getMarca();
		String referencia=producto.getReferencia();
		Integer stock = producto.getStock();
		Integer stockSeguridad = producto.getStockSeguridad();
		
		if (stock!=null &&stockSeguridad!=null && stock<=stockSeguridad) {
			errors.rejectValue("stock", " debe ser mayor al stock actual", " debe ser mayor al stock actual");
		}
		if (stock==null) {
			errors.rejectValue("stock", "no puede ser null", "no puede ser null");
		}
		if (stockSeguridad==null) {
			errors.rejectValue("stockSeguridad", "no puede ser null", "no puede ser null");
		}
		if (marca.startsWith(" ") || marca.isEmpty()) {
			errors.rejectValue("marca", "no puede empezar por espacio ni estar vacio", "no puede empezar por espacio ni estar vacio");
		}
		if (nombre.startsWith(" ") || nombre.isEmpty()) {
			errors.rejectValue("nombre", "no puede empezar por espacio ni estar vacio", "no puede empezar por espacio ni estar vacio");
		}
		if (referencia.startsWith(" ") || referencia.isEmpty()) {
			errors.rejectValue("referencia", "no puede empezar por espacio ni estar vacio", "no puede empezar por espacio ni estar vacio");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Producto.class.isAssignableFrom(clazz);
	}

}
