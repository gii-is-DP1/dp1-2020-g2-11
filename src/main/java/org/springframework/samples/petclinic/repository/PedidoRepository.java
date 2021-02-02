package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

	@Query("SELECT pedido FROM Pedido pedido WHERE pedido.fechaEntrada =:fechaEntrada")
	public Collection<Pedido> findByFechaPedido(@Param("fechaEntrada") LocalDate fechaEntrada);
	
}
