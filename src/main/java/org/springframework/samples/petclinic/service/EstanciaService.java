package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.repository.EstanciaRepository;
import org.springframework.samples.petclinic.service.exceptions.SobrecargaDeVehiculosException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.samples.petclinic.model.Estancia;

@Service
public class EstanciaService {
	private EstanciaRepository estanciaRepository;
	

	@Autowired
	public EstanciaService(EstanciaRepository estanciaRepository) throws DataAccessException {
		this.estanciaRepository = estanciaRepository;
	}

	@Transactional
	public void saveEstancia(Estancia estancia) throws DataAccessException, SobrecargaDeVehiculosException {
		int n_otherEstancias = estanciaRepository.findByFechaEstancia(estancia.getFechaEntrada()).size();
		if (n_otherEstancias  <= 2) {
			estanciaRepository.save(estancia);
		} else {
			throw new SobrecargaDeVehiculosException();
		}
		
	}

	@Transactional(readOnly = true)
	public Collection<Estancia> findEstanciasActuales() throws DataAccessException {
		return estanciaRepository.findByEstacionados();
	}

	@Transactional(readOnly = true)
	public Collection<Estancia> findAllEstancia() throws DataAccessException {
		return (Collection<Estancia>) estanciaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Collection<Estancia> findEstanciaByFechaEstancia(LocalDate fecha) {
		return estanciaRepository.findByFechaEstancia(fecha);
	}
}
