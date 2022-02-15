package br.com.desafiopd.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiopd.exception.ResourceNotFoundException;
import br.com.desafiopd.model.dto.ReportDto;
import br.com.desafiopd.model.dto.ReportEmployeeHourDto;
import br.com.desafiopd.model.dto.ReportSquadAverageHoursPerDayDto;
import br.com.desafiopd.model.dto.ReportSquadHourDto;
import br.com.desafiopd.model.dto.ResponseDto;
import br.com.desafiopd.model.entities.Report;
import br.com.desafiopd.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportRestController {

	@Autowired
	ReportService reportService;
	
	@PostMapping
	public ResponseEntity<Object> createReport(@Valid @RequestBody Report report) throws Exception {

		ResponseDto responseDto = new ResponseDto();
		
		try {
			
			Report newReport = reportService.create(report);
			return ResponseEntity.ok(newReport);
			
		} catch (ResourceNotFoundException e) {
			
			responseDto.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
			
		} catch (Exception e) {
			
			responseDto.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			
		}

	}
	
	@GetMapping("/get-total-hours-squad-member")
	public ResponseEntity<Object> getTotalHoursSquadMember(@RequestParam Long squadId, String initialDate, String finalDate) throws Exception {
		
		ResponseDto responseDto = new ResponseDto();
		
		try {
									
			List<ReportEmployeeHourDto> reportResult = reportService.getTotalHoursSquadMember(squadId, Date.valueOf(initialDate), Date.valueOf(finalDate));
			return ResponseEntity.ok(reportResult);
			
		} catch (Exception e) {
			
			responseDto.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			
		}
		
	}
	
	@GetMapping("/get-total-hours-squad")
	public ResponseEntity<Object> getTotalHoursSquad(@RequestParam Long squadId, String initialDate, String finalDate) throws Exception {
		
		ResponseDto responseDto = new ResponseDto();
		
		try {
									
			ReportSquadHourDto reportResult = reportService.getTotalHoursSquad(squadId, Date.valueOf(initialDate), Date.valueOf(finalDate));
			return ResponseEntity.ok(reportResult);
			
		} catch (Exception e) {
			
			responseDto.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			
		}
		
	}
	
	@GetMapping("/get-average-hours-squad-per-day")
	public ResponseEntity<Object> getAverageHoursSquadPerDay(@RequestParam Long squadId, String initialDate, String finalDate) throws Exception {
		
		ResponseDto responseDto = new ResponseDto();
		
		try {
									
			ReportSquadAverageHoursPerDayDto reportResult = reportService.getAverageHoursSquadPerDay(squadId, Date.valueOf(initialDate), Date.valueOf(finalDate));
			return ResponseEntity.ok(reportResult);
			
		} catch (Exception e) {
			
			responseDto.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			
		}
		
	}
	
	@GetMapping("/get-reports-by-squad")
	public ResponseEntity<Object> getReportsBySquadBetweenDate(@RequestParam Long squadId, String initialDate, String finalDate) throws Exception {
		
		ResponseDto responseDto = new ResponseDto();
		
		try {
									
			List<ReportDto> reportResult = reportService.getReportsBySquadBetweenDate(squadId, Date.valueOf(initialDate), Date.valueOf(finalDate));
			return ResponseEntity.ok(reportResult);
			
		} catch (Exception e) {
			
			responseDto.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			
		}
		
	}
	
}
