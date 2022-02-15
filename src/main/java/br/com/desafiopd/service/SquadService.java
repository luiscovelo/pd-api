package br.com.desafiopd.service;

import java.util.List;
import java.util.Optional;

import br.com.desafiopd.model.entities.Squad;

public interface SquadService {
	
	public Optional<Squad> findById(Long id) throws Exception;
	
	public Squad create(Squad squad);
	
	public List<Squad> findAll();
	
	public int getCountEmployees(Long id);
	
}
