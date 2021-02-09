package org.springframework.samples.petclinic.web;


import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
public class FacturaValidator implements Validator {
	
	@Override
	public void validate(Object obj, Errors errors) {

		Factura factura= (Factura) obj;
		LocalDate fechaEmision=factura.getFechaEmision();
		Boolean pagado=factura.getPagado();
		Double precio= factura.getPrecio();
		TipoPago t= factura.getTipoPago();
		Cliente cliente=factura.getCliente();
		if (precio == null) {
			errors.rejectValue("precio", " El precio de la factura no puede estar vacío",
					" El precio de la factura no puede estar vacío");
		}
		
		if (precio<=0) {
			errors.rejectValue("precio", " El precio de la factura no puede ser negativo",
					" El precio de la factura no puede ser negativo");
		
		}
		if (precio >=300 && t.equals(TipoPago.EFECTIVO)) {
			errors.rejectValue("precio", " No puedes abonar la cantidad de "+ precio+" en efectivo el maximo es 299.99",
					" No puedes abonar la cantidad de "+ precio+" en efectivo el maximo es 299.99");
		}
		
		if (fechaEmision==null) {
			errors.rejectValue("fechaEmision", " La fecha de emisión de la factura no puede estar vacío",
					" La fecha de emisión de la factura no puede estar vacío");
		}
		
		if (pagado==null) {
			errors.rejectValue("pagado", " Este campo no puede estar vacío y se debe completar con true or false",
					" Este campo no puede estar vacío y se debe completar con true or false");
		}
		
		if (cliente==null) {
			errors.rejectValue("cliente", " Este campo no puede estar vacío",
					" Este campo no puede estar vacío");
		}
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Factura.class.isAssignableFrom(clazz);
	}
}
