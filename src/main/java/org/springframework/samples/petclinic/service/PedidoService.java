package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {
	
	private PedidoRepository pedidoRepository;
	
	@Autowired
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository=pedidoRepository;
	}
		
	@Transactional(readOnly=true)
	public Collection<Pedido> findAllPedidos() throws DataAccessException{
		return (Collection<Pedido>) pedidoRepository.findAll();
	}
	
	@Transactional
	public Optional<Pedido> findById(Integer Id) throws DataAccessException{
		return pedidoRepository.findById(Id);
	}
	
	@Transactional
	public void savePedido(Pedido pedido) throws DataAccessException{
		pedidoRepository.save(pedido);
	}
	
	@Transactional
	public Collection<Pedido> findPedidoByfechaDeEmision(LocalDate fechaEmision){
		return pedidoRepository.findByFechaPedido(fechaEmision);
	}
	
}
