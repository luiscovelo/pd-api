package br.com.desafiopd.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiopd.exception.ResourceNotFoundException;
import br.com.desafiopd.model.entities.Squad;
import br.com.desafiopd.repository.SquadRepository;
import br.com.desafiopd.service.SquadService;

@Service
public class SquadServiceImpl implements SquadService {
	
	@Autowired
	SquadRepository squadRepository;
	
	@Override
	public Optional<Squad> findById(Long id) throws Exception {
		
		Optional<Squad> squad = squadRepository.findById(id);
		
		if(squad.isEmpty()) {
			throw new ResourceNotFoundException("A squad informada não foi encontrada");
		}
		
		return squad;
	}
	
	@Override
	public Squad create(Squad squad) {
		return squadRepository.save(squad);
	}

	@Override
	public List<Squad> findAll() {
		return squadRepository.findAll();
	}

	@Override
	public int getCountEmployees(Long id) {
		return squadRepository.getCountEmployees(id);
	}

}
