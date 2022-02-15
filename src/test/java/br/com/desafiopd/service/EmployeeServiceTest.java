package br.com.desafiopd.service;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.desafiopd.exception.ResourceNotFoundException;
import br.com.desafiopd.model.entities.Employee;
import br.com.desafiopd.repository.EmployeeRepository;
import br.com.desafiopd.repository.SquadRepository;

@SpringBootTest
public class EmployeeServiceTest {
	
	@MockBean
	EmployeeService employeeService;
	
	@MockBean
	EmployeeRepository employeeRepository;
	
	@MockBean
	SquadRepository squadRepository;
	
	@BeforeEach
	public void setUp() {
		
		standaloneSetup(employeeService);
		standaloneSetup(employeeRepository);
		standaloneSetup(squadRepository);
		
	}
	
	@Test
	public void shouldBeFindById() throws Exception {
		
		Optional<Employee> employee = Optional.of(new Employee());
		
		when(employeeService.findById(Mockito.anyLong())).thenReturn(employee);
		
		Optional<Employee> expected = employeeService.findById(1L);
		
		assertEquals(expected, employee);
		
	}
	
	@Test
	public void shouldBeResourceNotFoundExceptionFindByIdWhenEmployeeNotFound() {
		
		when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		try {
			
			employeeService.findById(Mockito.anyLong());
			
		} catch (Exception e) {
			assertThat(e instanceof ResourceNotFoundException);
		}
		
	}
	
	@Test
	public void shouldBeCreateEmployee() throws Exception {
		
		Employee employee = new Employee();
		
		when(employeeService.create(Mockito.any(Employee.class))).thenReturn(employee);
		
		Employee expected = employeeService.create(employee);
		
		assertEquals(expected, employee);
		
	}
	
	@Test
	public void shouldBeResourceNotFoundExceptionCreateWhenSquadNotFound() {
		
		when(squadRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		try {
			
			employeeService.create(Mockito.any(Employee.class));
			
		} catch (Exception e) {
			assertThat(e instanceof ResourceNotFoundException);
		}
		
	}
	
	@Test
	public void shouldBeFindAll() {
		
		List<Employee> employees = new ArrayList<>();
		
		when(employeeService.findAll()).thenReturn(employees);
		
		List<Employee> expected = employeeService.findAll();
		
		assertEquals(expected, employees);
		
	}
	
}
