package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Factura;

public interface FacturaRepository extends CrudRepository<Factura, Integer>{
	
	
	@Query("SELECT f FROM Factura f WHERE f.fechaEmision = :fechaEmision")
	public Factura findByFechaEmision(@Param("fechaEmision") LocalDate fechaEmision);

	@Query("SELECT f FROM Factura f WHERE f.pagado = :pagado")
	public Factura findByPagado(@Param("pagado") Boolean pagado);
	
	@Query("SELECT f FROM Factura f, Cliente c WHERE factura.cliente = :cliente")
    public Factura findByCliente(@Param("cliente") Cliente cliente);
	
//	void update(Factura factura) throws DataAccessException;
	
	Collection<Factura> findAll() throws DataAccessException;
	
	
}
