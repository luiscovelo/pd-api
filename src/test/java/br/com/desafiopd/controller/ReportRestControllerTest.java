package br.com.desafiopd.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.desafiopd.exception.ResourceNotFoundException;
import br.com.desafiopd.model.dto.ReportSquadHourDto;
import br.com.desafiopd.model.entities.Report;
import br.com.desafiopd.service.ReportService;

@WebMvcTest(ReportRestController.class)
public class ReportRestControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ReportService reportService;
	
	@Test
	public void shouldBeCreateReport() throws Exception {
		
		Report report = new Report();
		
		report.setDescription("descricao");
		report.setEmployeeId(1L);
		report.setSpentHours(8);
		
		when(reportService.create(report)).thenReturn(report);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(report);
		
		mockMvc.perform(post("/report")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json))
        	.andExpect(status().isOk());
		
	}
	
	@Test
	public void shouldBeReturnHttpStatusBadRequestWithDescriptionBlank() throws Exception {
		
		Report report = new Report();
		
		report.setDescription("");
		report.setEmployeeId(1L);
		report.setSpentHours(8);
		
		when(reportService.create(report)).thenReturn(report);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(report);
		
		mockMvc.perform(post("/report")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json))
        	.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeReturnHttpStatusBadRequestWithEmployeeIdZero() throws Exception {
		
		Report report = new Report();
		
		report.setDescription("descricao");
		report.setEmployeeId(0L);
		report.setSpentHours(8);
		
		when(reportService.create(report)).thenReturn(report);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(report);
		
		mockMvc.perform(post("/report")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json))
        	.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeReturnHttpStatusBadRequestWithSpentHoursZero() throws Exception {
		
		Report report = new Report();
		
		report.setDescription("descricao");
		report.setEmployeeId(1L);
		report.setSpentHours(0);
		
		when(reportService.create(report)).thenReturn(report);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(report);
		
		mockMvc.perform(post("/report")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json))
        	.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeReturnHttpStatusNotFoundWhenEmployeeNotFound() throws Exception {
		
		Report report = new Report();
		
		report.setDescription("descricao");
		report.setEmployeeId(1L);
		report.setSpentHours(8);
		
		when(reportService.create(Mockito.any(Report.class))).thenThrow(ResourceNotFoundException.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(report);
		
		mockMvc.perform(post("/report")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json))
        	.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void shouldBeGetTotalHoursSquadMember() throws Exception {
		
		when(reportService.getTotalHoursSquadMember(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
		
		mockMvc
			.perform(get("/report/get-total-hours-squad-member?squadId=1&initialDate=2022-02-01&finalDate=2022-02-28"))
			.andDo(print())
			.andExpect(status().isOk());
		
	}
	
	@Test
	public void shouldBeHttpStatusBadRequestGetTotalHoursSquadMemberWithMissingParamSquadId() throws Exception {
		
		when(reportService.getTotalHoursSquadMember(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
		
		mockMvc
			.perform(get("/report/get-total-hours-squad-member?initialDate=2022-02-01&finalDate=2022-02-28"))
			.andDo(print())
			.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeHttpStatusBadRequestGetTotalHoursSquadMemberWithMissingParamInitialDate() throws Exception {
		
		when(reportService.getTotalHoursSquadMember(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
		
		mockMvc
			.perform(get("/report/get-total-hours-squad-member?squadId=1&finalDate=2022-02-28"))
			.andDo(print())
			.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeHttpStatusBadRequestGetTotalHoursSquadMemberWithMissingParamFinalDate() throws Exception {
		
		when(reportService.getTotalHoursSquadMember(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
		
		mockMvc
			.perform(get("/report/get-total-hours-squad-member?squadId=1&initialDate=2022-02-01"))
			.andDo(print())
			.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeGetTotalHoursSquad() throws Exception {
		
		when(reportService.getTotalHoursSquad(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(Mockito.any(
				ReportSquadHourDto.class));
		
		mockMvc
			.perform(get("/report/get-total-hours-squad?squadId=1&initialDate=2022-02-01&finalDate=2022-02-28"))
			.andDo(print())
			.andExpect(status().isOk());
		
	}
	
	@Test
	public void shouldBeHttpStatusBadRequestGetTotalHoursSquadWithMissingParamSquadId() throws Exception {
		
		when(reportService.getTotalHoursSquad(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(Mockito.any(
				ReportSquadHourDto.class));
		
		mockMvc
			.perform(get("/report/get-total-hours-squad?initialDate=2022-02-01&finalDate=2022-02-28"))
			.andDo(print())
			.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeHttpStatusBadRequestGetTotalHoursSquadWithMissingParamInitialdate() throws Exception {
		
		when(reportService.getTotalHoursSquad(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(Mockito.any(
				ReportSquadHourDto.class));
		
		mockMvc
			.perform(get("/report/get-total-hours-squad?squadId=1&finalDate=2022-02-28"))
			.andDo(print())
			.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeHttpStatusBadRequestGetTotalHoursSquadWithMissingParamFinaldate() throws Exception {
		
		when(reportService.getTotalHoursSquad(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(Mockito.any(ReportSquadHourDto.class));
		
		mockMvc
			.perform(get("/report/get-total-hours-squad?squadId=1&initialDate=2022-02-01"))
			.andDo(print())
			.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeGetAverageHoursSquadPerDay() throws Exception {
		
		when(reportService.getAverageHoursSquadPerDay(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(null);
		
		mockMvc
			.perform(get("/report/get-average-hours-squad-per-day?squadId=1&initialDate=2022-02-01&finalDate=2022-02-28"))
			.andDo(print())
			.andExpect(status().isOk());
		
	}
	
	@Test
	public void shouldBeHttpStatusBadRequestGetAverageHoursSquadPerDayWithMissingParamSquadId() throws Exception {
		
		when(reportService.getAverageHoursSquadPerDay(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(null);
		
		mockMvc
			.perform(get("/report/get-average-hours-squad-per-day?initialDate=2022-02-01&finalDate=2022-02-28"))
			.andDo(print())
			.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeHttpStatusBadRequestGetAverageHoursSquadPerDayWithMissingParamInitialdate() throws Exception {
		
		when(reportService.getAverageHoursSquadPerDay(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(null);
		
		mockMvc
			.perform(get("/report/get-average-hours-squad-per-day?squadId=1&finalDate=2022-02-28"))
			.andDo(print())
			.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeHttpStatusBadRequestGetAverageHoursSquadPerDayWithMissingParamFinaldate() throws Exception {
		
		when(reportService.getAverageHoursSquadPerDay(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(null);
		
		mockMvc
			.perform(get("/report/get-total-hours-squad?squadId=1&initialDate=2022-02-01"))
			.andDo(print())
			.andExpect(status().isBadRequest());
		
	}
	
}
