package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Pedido;

public interface PedidoRepository extends Repository<Pedido, Integer> {
	void save(Pedido pedido) throws DataAccessException;

	@Query("SELECT pedido FROM Pedido pedido WHERE pedido.fechaEntrada =:fechaEntrada")
	public Collection<Pedido> findByFechaPedido(@Param("fechaEntrada") LocalDate fechaEntrada);
	
	@Query("SELECT pedido FROM Pedido pedido WHERE pedido.id =:id")
	public Optional<Pedido> findById(@Param("id") int id);
	
	Collection<Pedido> findAll() throws DataAccessException;

}
