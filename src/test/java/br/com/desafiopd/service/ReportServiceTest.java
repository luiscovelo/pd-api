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
import br.com.desafiopd.model.dto.ReportEmployeeHourDto;
import br.com.desafiopd.model.dto.ReportSquadAverageHoursPerDayDto;
import br.com.desafiopd.model.dto.ReportSquadHourDto;
import br.com.desafiopd.model.entities.Report;
import br.com.desafiopd.repository.EmployeeRepository;

@SpringBootTest
public class ReportServiceTest {
	
	@MockBean
	ReportService reportService;
	
	@MockBean
	EmployeeRepository employeeRepository;
	
	@BeforeEach
	public void setUp() {
		
		standaloneSetup(reportService);
		standaloneSetup(employeeRepository);
		
	}

	@Test
	public void shouldBeCreateReport() throws Exception {
		
		Report report = new Report();
		
		when(reportService.create(Mockito.any(Report.class))).thenReturn(report);
		
		Report expected = reportService.create(report);
		
		assertEquals(expected, report);
		
	}
	
	@Test
	public void shouldBeReturnResourceNotFoundExceptionWhenEmployeeNotFound() {
		
		when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		try {
			
			reportService.create(Mockito.any(Report.class));
			
		} catch (Exception e) {
			
			assertThat(e instanceof ResourceNotFoundException);
			
		}
		
	}
	
	@Test
	public void shouldBeGetTotalHoursSquadMember() throws Exception {
		
		List<ReportEmployeeHourDto> report = new ArrayList<>();
		
		when(reportService.getTotalHoursSquadMember(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(report);
		
		List<ReportEmployeeHourDto> expected = reportService.getTotalHoursSquadMember(Mockito.anyLong(), Mockito.any(), Mockito.any());
		
		assertEquals(expected, report);
		
	}
	
	@Test
	public void shouldBeGetTotalHoursSquad() throws Exception {
		
		ReportSquadHourDto report = null;
		
		when(reportService.getTotalHoursSquad(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(report);
		
		ReportSquadHourDto expected = reportService.getTotalHoursSquad(Mockito.anyLong(), Mockito.any(), Mockito.any());
		
		assertEquals(expected, report);
		
	}
	
	@Test
	public void shouldBeGetAverageHoursSquadPerDate() throws Exception {
		
		ReportSquadAverageHoursPerDayDto report = null;
		
		when(reportService.getAverageHoursSquadPerDay(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(report);
		
		ReportSquadAverageHoursPerDayDto expected = reportService.getAverageHoursSquadPerDay(Mockito.anyLong(), Mockito.any(), Mockito.any());
		
		assertEquals(expected, report);
		
	}
	
}
