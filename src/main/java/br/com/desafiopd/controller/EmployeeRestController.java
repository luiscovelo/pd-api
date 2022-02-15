package br.com.desafiopd.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiopd.exception.ResourceNotFoundException;
import br.com.desafiopd.model.dto.ResponseDto;
import br.com.desafiopd.model.entities.Employee;
import br.com.desafiopd.service.EmployeeService;
import br.com.desafiopd.service.SquadService;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	SquadService squadService;
	
	@GetMapping
	public ResponseEntity<List<Employee>> findAll() {

		List<Employee> employees = employeeService.findAll();

		return ResponseEntity.ok(employees);

	}
	
	@PostMapping
	public ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee employee) {
		
		ResponseDto responseDto = new ResponseDto();
		
		try {
						
			Employee newEmployee = employeeService.create(employee);
			return ResponseEntity.ok(newEmployee);
			
		} catch (ResourceNotFoundException e) {
			
			responseDto.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
			
		} catch (Exception e) {
			
			responseDto.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		
		ResponseDto responseDto = new ResponseDto();
		
		try {
			
			Optional<Employee> employee = employeeService.findById(id);
			return ResponseEntity.ok(employee);
			
		} catch (ResourceNotFoundException e) {
			
			responseDto.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
			
		} catch (Exception e) {
			
			responseDto.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			
		}

	}
	
}
