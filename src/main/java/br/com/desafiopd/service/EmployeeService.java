package br.com.desafiopd.service;

import java.util.List;
import java.util.Optional;

import br.com.desafiopd.model.entities.Employee;

public interface EmployeeService {
	
	public Optional<Employee> findById(Long id) throws Exception;
	
	public Employee create(Employee employee) throws Exception;
	
	public List<Employee> findAll();
	
}
