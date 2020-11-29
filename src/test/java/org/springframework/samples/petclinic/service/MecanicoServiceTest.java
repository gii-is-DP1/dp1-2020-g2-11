package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Mecanico;
import org.springframework.samples.petclinic.model.User;
import org.springframework.transaction.annotation.Transactional;

public class MecanicoServiceTest {
    @Autowired
    protected MecanicoService mecanicoService;

//    private Mecanico mecanico;
    private User user;

       @BeforeEach
//    void setup() {
//
//        mecanico = mecanicoService.findById(1);
//
//    }

    @Test
    @Transactional
    void nuevoMecanico() throws DataAccessException {

        Mecanico mecanico1 = new Mecanico();
        User usuario1 = new User();
        mecanico1.setNombre("Juan");
        mecanico1.setApellidos("Pérez");
        mecanico1.setDni("788624578K");
        mecanico1.setId(2);
        mecanico1.setEmail("juanperez9@gmail.com");
        mecanico1.setTelefono("644895623");

        usuario1.setUsername("juanperez1");
        usuario1.setPassword("jueanperez1");
        mecanico1.setUser(usuario1);

        this.mecanicoService.saveMecanico(mecanico1);
        Collection<Mecanico> mecanicos = this.mecanicoService.findMecanico();
        assertThat(mecanicos.size()).isEqualTo(1);

    }

}