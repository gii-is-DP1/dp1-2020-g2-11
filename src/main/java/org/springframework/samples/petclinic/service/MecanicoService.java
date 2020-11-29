package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Mecanico;
import org.springframework.samples.petclinic.repository.MecanicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MecanicoService {

	private MecanicoRepository mecanicoRepository;

	@Autowired
	public MecanicoService(MecanicoRepository mecanicoRepository) {
		this.mecanicoRepository = mecanicoRepository;
	}

	@Transactional(readOnly = true)
	public Collection<Mecanico> findMecanico() throws DataAccessException {
		return mecanicoRepository.findAll();
	}

	@Transactional
	public void saveMecanico(Mecanico mecanico) throws DataAccessException {
		mecanicoRepository.save(mecanico);
	}
	
	@Transactional
    public Mecanico findById(Integer id) throws DataAccessException {
        return mecanicoRepository.findById(id);
    }

}
