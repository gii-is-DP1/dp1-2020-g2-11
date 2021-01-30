package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Estancia;
import org.springframework.samples.petclinic.repository.CitaRepository;
import org.springframework.samples.petclinic.repository.EstanciaRepository;
import org.springframework.samples.petclinic.repository.MecanicoRepository;
import org.springframework.samples.petclinic.service.exceptions.SobrecargaDeVehiculosException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CitaService {

	private CitaRepository citaRepository;
	private EstanciaService estanciaService;
	
	
	@Autowired
	public CitaService(CitaRepository citaRepository) {
		this.citaRepository = citaRepository;
		
	}
	
	
	
	
	
	@Transactional
	public void saveCita(Cita cita) throws DataAccessException, SobrecargaDeVehiculosException {
		int n_otherCitas= citaRepository.findByFechaCita(cita.getFechaCita()).size();
		int n_otherEstancias= estanciaService.findEstanciaByFechaCita(cita.getFechaCita()).size();
		if (n_otherCitas<=2||n_otherEstancias+n_otherCitas<=2) {
			
		}else {
			throw new SobrecargaDeVehiculosException();
		}
		citaRepository.save(cita);
	}
	
	@Transactional(readOnly=true)
	public Collection<Cita> findCitaByFechaCita(LocalDate fecha) {
		return citaRepository.findByFechaCita(fecha);
	}
	
	@Transactional
	public void removeCita(Integer id)  throws DataAccessException{
		citaRepository.deleteById(id);
	}
	@Transactional(readOnly = true)
	public Collection<Cita> findAllCita() throws DataAccessException{
		return (Collection<Cita>) citaRepository.findAll();
	}
	

}
