

package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Mecanico;

public interface MecanicoRepository extends CrudRepository<Mecanico, Integer>{
	
	@Query("SELECT mecanico FROM Mecanico mecanico WHERE mecanico.id =:id")
    public Mecanico findById(@Param("id") int id);

	
}
