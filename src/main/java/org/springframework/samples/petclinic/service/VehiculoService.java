package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.repository.VehiculoRepository;
import org.springframework.samples.petclinic.service.exceptions.VehiculosAntiguo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
	@Service
	public class VehiculoService {

		private VehiculoRepository vehiculoRepository;

		@Autowired
		public VehiculoService(VehiculoRepository vehiculoRepository) {
		this.vehiculoRepository = vehiculoRepository;
		}

		@Transactional(readOnly = true)
		public Vehiculo findVehiculoById(Integer id) throws DataAccessException {
			return vehiculoRepository.findById(id).get();
		}


		@Transactional(readOnly = true)
		public Vehiculo findVehiculoByMatricula(String matricula) throws DataAccessException {
			return vehiculoRepository.findByMatricula(matricula);
		}

		@Transactional(readOnly = true)
		public Collection<Vehiculo> findVehiculoByTipo(TipoVehiculo tipo) throws DataAccessException {
			return vehiculoRepository.findByTipoVehiculo(tipo);
		}

		@Transactional(readOnly = true)
		public Collection<Vehiculo> findVehiculoByCliente(Integer clienteId) throws DataAccessException {
			return vehiculoRepository.findByCliente(clienteId);
		}
		
		@Transactional(readOnly = true)
		public Collection<Vehiculo> findVehiculos() throws DataAccessException {
			return (Collection<Vehiculo>) vehiculoRepository.findAll();
		}

		@Transactional(rollbackFor = VehiculosAntiguo.class)
		public void saveVehiculo(Vehiculo vehiculo) throws DataAccessException, VehiculosAntiguo {
			if (vehiculo.getFechaFabricacion().getYear() - LocalDate.now().getYear() < 12) {
				vehiculoRepository.save(vehiculo);
			} else {
				throw new VehiculosAntiguo();
			}
		}


	}



