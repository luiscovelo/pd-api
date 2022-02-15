package br.com.desafiopd.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiopd.exception.ResourceNotFoundException;
import br.com.desafiopd.model.entities.Employee;
import br.com.desafiopd.model.entities.Squad;
import br.com.desafiopd.repository.EmployeeRepository;
import br.com.desafiopd.repository.SquadRepository;
import br.com.desafiopd.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	SquadRepository squadRepository;
	
	@Override
	public Optional<Employee> findById(Long id) throws Exception {
		
		Optional<Employee> employee = employeeRepository.findById(id);
		
		if(employee.isEmpty()) {
			throw new ResourceNotFoundException("O funcionário informado não foi encontrado.");
		}
		
		return employeeRepository.findById(id);
	}

	@Override
	public Employee create(Employee employee) throws Exception {
		
		Optional<Squad> squad = squadRepository.findById(employee.getSquadId());
		
		if(squad.isEmpty()) {
			throw new ResourceNotFoundException("A squad informada não foi encontrada.");
		}
		
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

}
