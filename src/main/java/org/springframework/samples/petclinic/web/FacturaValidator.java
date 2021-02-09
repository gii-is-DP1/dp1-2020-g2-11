package org.springframework.samples.petclinic.web;


import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
public class FacturaValidator implements Validator {
	
	@Override
	public void validate(Object obj, Errors errors) {

		Factura factura= (Factura) obj;
		Double precio= factura.getPrecio();
		TipoPago t= factura.getTipoPago();
		if (precio<0) {
			errors.rejectValue("precio", " El precio de la factura no puede ser negativo",
					" El precio de la factura no puede ser negativo");
		
		}
		if (precio >=300 && t.equals(TipoPago.EFECTIVO)) {
			errors.rejectValue("precio", " No puedes abonar la cantidad de "+ precio+" en efectivo el maximo es 299.99",
					" No puedes abonar la cantidad de "+ precio+" en efectivo el maximo es 299.99");
		}
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Factura.class.isAssignableFrom(clazz);
	}
}
