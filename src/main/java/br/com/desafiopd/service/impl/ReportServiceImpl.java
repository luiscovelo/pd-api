package br.com.desafiopd.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiopd.exception.ResourceNotFoundException;
import br.com.desafiopd.model.dto.ReportDto;
import br.com.desafiopd.model.dto.ReportEmployeeHourDto;
import br.com.desafiopd.model.dto.ReportSquadAverageHoursPerDayDto;
import br.com.desafiopd.model.dto.ReportSquadHourDto;
import br.com.desafiopd.model.entities.Employee;
import br.com.desafiopd.model.entities.Report;
import br.com.desafiopd.repository.EmployeeRepository;
import br.com.desafiopd.repository.ReportRepository;
import br.com.desafiopd.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	ReportRepository reportRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Report create(Report report) throws Exception {
		
		Optional<Employee> employee = employeeRepository.findById(report.getEmployeeId());
		
		if(employee.isEmpty()) {
			throw new ResourceNotFoundException("O funcionário informado não foi encontrado.");
		}
		
		return reportRepository.save(report);
	}

	@Override
	public List<ReportEmployeeHourDto> getTotalHoursSquadMember(Long squaId, Date initialDate, Date finalDate) throws Exception {
		return reportRepository.getTotalHoursSquadMember(squaId, initialDate, finalDate);
	}

	@Override
	public ReportSquadHourDto getTotalHoursSquad(Long squaId, Date initialDate, Date finalDate) throws Exception {
		return reportRepository.getTotalHoursSquad(squaId, initialDate, finalDate);
	}

	@Override
	public ReportSquadAverageHoursPerDayDto getAverageHoursSquadPerDay(Long squaId, Date initialDate, Date finalDate) throws Exception {
		return reportRepository.getAverageHoursSquadPerDate(squaId, initialDate, finalDate);
	}

	@Override
	public List<ReportDto> getReportsBySquadBetweenDate(Long squaId, Date initialDate, Date finalDate) throws Exception {
		return reportRepository.getReportsBySquadBetweenDate(squaId, initialDate, finalDate);
	}

}
