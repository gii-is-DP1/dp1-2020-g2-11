package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Estancia;
import org.springframework.samples.petclinic.repository.CitaRepository;
import org.springframework.samples.petclinic.repository.EstanciaRepository;
import org.springframework.samples.petclinic.service.exceptions.SobrecargaDeVehiculosException;
import org.springframework.transaction.annotation.Transactional;

public class CitaService {

	private CitaRepository citaRepository;
	private EstanciaRepository estanciaRepository;
	
	@Transactional(rollbackFor = SobrecargaDeVehiculosException.class)
	public void saveCita(Cita cita) throws DataAccessException,SobrecargaDeVehiculosException {
		int n_otherCitas= citaRepository.findByFechaCita(cita.getFechaCita()).size();
		int n_otherEstancias= estanciaRepository.findByEstacionados(null).size();
		if (n_otherCitas<=2||n_otherEstancias+n_otherCitas<=2) {
			citaRepository.save(cita);
		}else {
			throw new SobrecargaDeVehiculosException();
		}
	}
	public void saveEstancia(Estancia estancia) throws DataAccessException,SobrecargaDeVehiculosException{
		int n_otherEstancias= estanciaRepository.findByFechaEstancia(estancia.getFechaEntrada()).size();
		int n_otherCitas= citaRepository.findByFechaCita(estancia.getFechaEntrada()).size();
		if (n_otherCitas<=2||n_otherEstancias+n_otherCitas<=2) {
			estanciaRepository.save(estancia);
		}else {
			throw new SobrecargaDeVehiculosException();
		}
	}
	public Collection<Cita> findCitaByFechaCita(LocalDate fecha) {
		return citaRepository.findByFechaCita(fecha);
	}
	
	public Collection<Estancia> findEstanciasActuales() {
		return estanciaRepository.findByEstacionados(null);
	}
	
}
