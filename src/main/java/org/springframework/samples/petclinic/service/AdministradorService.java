package org.springframework.samples.petclinic.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.repository.AdministradorRepository;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {
	
	private AdministradorRepository administradorRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthoritiesService authoritiesService;
//	@Transactional
//	public void updateAdministrador(Integer id) {
//		administradorRepository.update(id);
//	}

	@Transactional
	public void saveAdministrador(Administrador administrador) throws DataAccessException {
		administradorRepository.save(administrador);
		userService.saveUser(administrador.getUser());
		authoritiesService.saveAuthorities(administrador.getUser().getUsername(), "administrador");
	}
	
	@Transactional
	public Collection<Administrador> findAdministrador() throws DataAccessException {
		return (Collection<Administrador>) administradorRepository.findAll();
	}
	
	
	
}
