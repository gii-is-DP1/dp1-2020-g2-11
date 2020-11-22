package org.springframework.samples.petclinic.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.repository.AdministradorRepository;

public class AdministradorService {
private AdministradorRepository administradorRepository;

@Autowired
public AdministradorService(AdministradorRepository AdministradorRepository) {
	this.administradorRepository= AdministradorRepository;
}
@Transactional
public void updateAdministrador(Administrador administrador) {
	administradorRepository.update(administrador);
}
@Transactional
public Collection<Administrador> findAdministradores() throws DataAccessException {
	return administradorRepository.findAll();
}
}
