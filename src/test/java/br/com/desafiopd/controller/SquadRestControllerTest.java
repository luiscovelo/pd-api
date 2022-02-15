package br.com.desafiopd.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import br.com.desafiopd.model.entities.Squad;
import br.com.desafiopd.repository.SquadRepository;
import br.com.desafiopd.service.SquadService;

@WebMvcTest(SquadRestController.class)
public class SquadRestControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	SquadService squadService;
	
	@MockBean
	SquadRepository squadRepository;
	
	@Test
	public void shouldBeFindAll() throws Exception {
		
		when(squadService.findAll()).thenReturn(new ArrayList<>());
		
		mockMvc
			.perform(get("/squad"))
			.andDo(print())
			.andExpect(status().isOk());
		
	}
	
	@Test
	public void shouldBeCreateSquad() throws Exception {
		
		Squad squad = new Squad();
		
		squad.setId(1L);
		squad.setName("Name");
		
		when(squadService.create(Mockito.any(Squad.class))).thenReturn(squad);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(squad);
		
		mockMvc.perform(post("/squad")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json))
        	.andExpect(status().isOk());
		
	}
	
	@Test
	public void shouldBeReturnBadRequestWhenCreateSquadWithoutName() throws Exception {
		
		Squad squad = new Squad();
		
		when(squadService.create(Mockito.any(Squad.class))).thenReturn(squad);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(squad);
		
		mockMvc.perform(post("/squad")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json))
        	.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void shouldBeFindById() throws Exception {
		
		Optional<Squad> squad = Optional.of(new Squad());
		
		when(squadService.findById(Mockito.anyLong())).thenReturn(squad);
		
		mockMvc
			.perform(get("/squad/{id}", 1L))
			.andDo(print())
			.andExpect(status().isOk());
		
	}
	
	@Test
	public void shouldBeReturnHttpStatusNotFoundWhenFindById() throws Exception {
		
		when(squadService.findById(Mockito.anyLong())).thenThrow(ResourceNotFoundException.class);
				
		mockMvc
			.perform(get("/squad/{id}", 1L))
			.andDo(print())
			.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void shouldBeReturnHttpStatusBadRequestWhenFindById() throws Exception {
		
		when(squadService.findById(Mockito.anyLong())).thenThrow(Exception.class);
				
		mockMvc
			.perform(get("/squad/{id}", 1L))
			.andDo(print())
			.andExpect(status().isBadRequest());
		
	}
	
}
