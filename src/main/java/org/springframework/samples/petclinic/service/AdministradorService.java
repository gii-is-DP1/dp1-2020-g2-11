package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.repository.AdministradorRepository;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {
	private AdministradorRepository administradorRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public AdministradorService(AdministradorRepository administradorRepository) {
		this.administradorRepository = administradorRepository;
	}

	@Transactional(readOnly = true)
	public Collection<Administrador> findAdministrador() throws DataAccessException {
		return (Collection<Administrador>) administradorRepository.findAll();
	}
	
	@Transactional
	public void saveAdministrador(Administrador admin) throws DataAccessException {
		//creating owner
		administradorRepository.save(admin);		
		//creating user
		userService.saveUser(admin.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(admin.getUser().getUsername(), "administrador");
	}
	
}
