package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Mecanico;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MecanicoServiceTest {
	
    @Autowired
    protected MecanicoService mecanicoService;

    @Test
    @Transactional
    public void shouldInsertMecanico() throws DataAccessException {

        Mecanico mecanico1 = new Mecanico();
        User usuario1 = new User();
        
        mecanico1.setNombre("Juan");
        mecanico1.setApellidos("Pérez");
        mecanico1.setDni("78862457K");
        mecanico1.setId(2);
        mecanico1.setEmail("juanperez9@gmail.com");
        mecanico1.setTelefono("644895623");

        usuario1.setUsername("juanperez1");
        usuario1.setPassword("juanperez1");
        mecanico1.setUser(usuario1);

        this.mecanicoService.saveMecanico(mecanico1);
        Collection<Mecanico> mecanicos = this.mecanicoService.findMecanico();
        assertThat(mecanicos.size()).isEqualTo(3);

    }
}