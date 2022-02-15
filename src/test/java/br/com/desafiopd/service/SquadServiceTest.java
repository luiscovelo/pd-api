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
import br.com.desafiopd.model.entities.Squad;
import br.com.desafiopd.repository.SquadRepository;

@SpringBootTest
public class SquadServiceTest {
	
	@MockBean
	SquadService squadService;
	
	@MockBean
	SquadRepository squadRepository;
	
	@BeforeEach
	public void setUp() {
		
		standaloneSetup(squadService);
		standaloneSetup(squadRepository);
		
	}
	
	@Test
	public void shouldBeCreateSquad() {
		
		Squad squad = new Squad();
		squad.setId(1L);
		
		when(squadService.create(Mockito.any(Squad.class))).thenReturn(squad);
		
		Squad expected = squadService.create(squad);
		
		assertEquals(expected, squad);
		
	}
	
	@Test
	public void shouldBeFindAll() {
		
		List<Squad> squads = new ArrayList<Squad>();
		
		when(squadService.findAll()).thenReturn(new ArrayList<Squad>());
		
		List<Squad> expected = squadService.findAll();
		
		assertEquals(expected, squads);
		
	}
	
	@Test
	public void shouldBeFindById() throws Exception {
		
		Optional<Squad> squad = Optional.of(new Squad());
		
		squad.get().setId(1L);
		
		when(squadService.findById(squad.get().getId())).thenReturn(squad);
		
		Optional<Squad> expected = squadService.findById(squad.get().getId());
		
		assertEquals(expected, squad);
		
	}
	
	@Test
	public void shouldBeReturnResourceNotFoundExceptionFindById() throws Exception {
		
		when(squadRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		try {
			
			squadService.findById(Mockito.anyLong());
			
		} catch (Exception e) {
			
			assertThat(e instanceof ResourceNotFoundException);
			
		}
		
	}
	
}
