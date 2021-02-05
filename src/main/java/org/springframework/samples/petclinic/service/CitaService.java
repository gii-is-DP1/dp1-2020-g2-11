package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.repository.CitaRepository;
import org.springframework.samples.petclinic.service.exceptions.SobrecargaDeVehiculosException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CitaService {

	private CitaRepository citaRepository;

	@Autowired
	public CitaService(CitaRepository citaRepository) {
		this.citaRepository = citaRepository;
	}

	@Transactional
	public void saveCita(Cita cita) throws DataAccessException, SobrecargaDeVehiculosException {
		int n_otherCitas = citaRepository.findByFechaCita(cita.getFechaCita()).size();
		if (n_otherCitas <= 12) {
			citaRepository.save(cita);
		} else {
			throw new SobrecargaDeVehiculosException();
		}
	}

	@Transactional(readOnly = true)
	public Collection<Cita> findCitaByFechaCita(LocalDate fecha) {
		return citaRepository.findByFechaCita(fecha);
	}

	@Transactional(readOnly = true)
	public Collection<Cita> findCitaByCliente(Integer clienteId) throws DataAccessException {
		return citaRepository.findByCliente(clienteId);
	}
//	@Transactional
//	public void removeCitaByCliente(int cliente) throws DataAccessException {
//		citaRepository.deleteByCliente(cliente);
//	}
	@Transactional
	public void removeCita(Integer id) throws DataAccessException {
		citaRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Cita> findAllCita() throws DataAccessException {
		return (Collection<Cita>) citaRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Cita findCitaById(Integer id) {
		return citaRepository.findById(id).get();
	}
}
