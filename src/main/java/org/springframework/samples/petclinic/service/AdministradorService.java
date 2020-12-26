package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.repository.AdministradorRepository;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {
	
	private AdministradorRepository administradorRepository;

//	@Transactional
//	public void updateAdministrador(Integer id) {
//		administradorRepository.update(id);
//	}

	@Transactional
	public void saveAdministrador(Administrador administrador) throws DataAccessException {
		administradorRepository.save(administrador);
	}
}
