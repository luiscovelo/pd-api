package br.com.desafiopd.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.desafiopd.exception.ResourceNotFoundException;
import br.com.desafiopd.model.entities.Employee;
import br.com.desafiopd.service.EmployeeService;
import br.com.desafiopd.service.SquadService;

@WebMvcTest(EmployeeRestController.class)
public class EmployeeRestControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	EmployeeService employeeService;
	
	@MockBean
	SquadService squadService;
	
	@Test
	public void shouldBeCreateEmployee() throws Exception {
		
		Employee employee = new Employee();
		
		employee.setId(1L);
		employee.setName("Luis");
		employee.setEstimatedHours(8);
		employee.setSquadId(1L);
		
		when(employeeService.create(Mockito.any(Employee.class))).thenReturn(employee);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(employee);
		
		mockMvc.perform(post("/employee")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json))
        	.andExpect(status().isOk());
		
	}
	
	@Test
	public void shouldBeReturnHttpStatusNotFoundWhenSquadNotFound() throws Exception {
		
		Employee employee = new Employee();
		
		employee.setId(1L);
		employee.setName("Luis");
		employee.setEstimatedHours(8);
		employee.setSquadId(1L);
		
		when(employeeService.create(Mockito.any(Employee.class))).thenThrow(ResourceNotFoundException.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(employee);
		
		mockMvc.perform(post("/employee")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json))
        	.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void shouldBeReturnHttpStatusBadRequestWhenNameIsBlank() throws Exception {
		
		Employee employee = new Employee();
		
		employee.setId(1L);
		employee.setName("");
		employee.setEstimatedHours(8);
		employee.setSquadId(1L);
		
		when(employeeService.create(Mockito.any(Employee.class))).thenReturn(employee);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(employee);
		
		mockMvc.perform(post("/employee")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json))
        	.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeReturnHttpStatusBadRequestWhenEstimatedHoursBurstMinRange() throws Exception {
		
		Employee employee = new Employee();
		
		employee.setId(1L);
		employee.setName("Luis");
		employee.setEstimatedHours(0);
		employee.setSquadId(1L);
		
		when(employeeService.create(Mockito.any(Employee.class))).thenReturn(employee);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(employee);
		
		mockMvc.perform(post("/employee")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json))
        	.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeReturnHttpStatusBadRequestWhenEstimatedHoursBurstMaxRange() throws Exception {
		
		Employee employee = new Employee();
		
		employee.setId(1L);
		employee.setName("Luis");
		employee.setEstimatedHours(13);
		employee.setSquadId(1L);
		
		when(employeeService.create(Mockito.any(Employee.class))).thenReturn(employee);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(employee);
		
		mockMvc.perform(post("/employee")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json))
        	.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeFindById() throws Exception {
		
		Optional<Employee> employee = Optional.of(new Employee());
		
		when(employeeService.findById(Mockito.anyLong())).thenReturn(employee);
		
		mockMvc
			.perform(get("/employee/{id}", 1L))
			.andDo(print())
			.andExpect(status().isOk());
		
	}
	
	@Test
	public void shouldBeReturnHttpStatusNotFoundWhenEmployeeNotFound() throws Exception {
		
		when(employeeService.findById(Mockito.anyLong())).thenThrow(ResourceNotFoundException.class);
		
		mockMvc
			.perform(get("/employee/{id}", 1L))
			.andDo(print())
			.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void shouldBeReturnHttpStatusBadRequest() throws Exception {
		
		when(employeeService.findById(Mockito.anyLong())).thenThrow(Exception.class);
		
		mockMvc
			.perform(get("/employee/{id}", 1L))
			.andDo(print())
			.andExpect(status().isBadRequest());
		
	}
	
}
