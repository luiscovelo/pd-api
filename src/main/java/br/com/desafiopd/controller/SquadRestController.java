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
import br.com.desafiopd.model.entities.Squad;
import br.com.desafiopd.service.SquadService;

@RestController
@RequestMapping("/squad")
public class SquadRestController {

	@Autowired
	SquadService squadService;

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) throws Exception {
		
		ResponseDto responseDto = new ResponseDto();
		
		try {
			
			Optional<Squad> squad = squadService.findById(id);
			return ResponseEntity.ok(squad);
			
		} catch (ResourceNotFoundException e) {
			
			responseDto.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
			
		} catch (Exception e) {
			
			responseDto.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			
		}

	}

	@PostMapping
	public ResponseEntity<Squad> createSquad(@Valid @RequestBody Squad squad) {

		Squad newSquad = squadService.create(squad);

		return ResponseEntity.ok(newSquad);

	}
	
	@GetMapping
	public ResponseEntity<List<Squad>> findAll() {

		List<Squad> squads = squadService.findAll();

		return ResponseEntity.ok(squads);

	}
	
	@GetMapping("/{id}/get-count-employees")
	public ResponseEntity<Integer> getCountEmployees(@PathVariable Long id) {

		int total = squadService.getCountEmployees(id);

		return ResponseEntity.ok(total);

	}
	
}
