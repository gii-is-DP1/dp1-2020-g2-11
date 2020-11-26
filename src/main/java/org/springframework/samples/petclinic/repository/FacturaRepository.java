package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Factura;

public interface FacturaRepository extends Repository<Factura, Integer>{
	
	
	@Query("SELECT factura FROM Factura factura left join fetch factura.fechaEmision, factura.precio, factura.pagado WHERE factura.id =:id")
	public Factura findById(@Param("id") int id);
	
	@Query("SELECT factura FROM Factura factura left join fetch factura.fechaEmision, factura.precio, factura.pagado WHERE factura.fechaEmision =:fechaEmision")
	public Factura findByFechaEmision(@Param("fechaEmision") LocalDate fechaEmision);

	@Query("SELECT factura FROM Factura factura left join fetch factura.fechaEmision, factura.precio, factura.pagado WHERE factura.pagado =:pagado")
	public Factura findByPagado(@Param("pagado") Boolean pagado);
	
	void save(Factura factura) throws DataAccessException;
	
	void update(Factura factura) throws DataAccessException;
	
	Collection<Factura> findAll() throws DataAccessException;
	
	
}
