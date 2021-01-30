package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

public class Productos {
	
	private List<Producto> productos;
	
	public List<Producto> getProductoList() {
		if (productos == null) {
			productos = new ArrayList<>();
		}
		return productos;
	}

}
