package br.com.desafiopd.service;

import java.sql.Date;
import java.util.List;

import br.com.desafiopd.model.dto.ReportDto;
import br.com.desafiopd.model.dto.ReportEmployeeHourDto;
import br.com.desafiopd.model.dto.ReportSquadAverageHoursPerDayDto;
import br.com.desafiopd.model.dto.ReportSquadHourDto;
import br.com.desafiopd.model.entities.Report;

public interface ReportService {
	
	public Report create(Report report) throws Exception;
	
	public List<ReportEmployeeHourDto> getTotalHoursSquadMember(Long squaId, Date initialDate, Date finalDate) throws Exception;
	
	public ReportSquadHourDto getTotalHoursSquad(Long squaId, Date initialDate, Date finalDate) throws Exception;
	
	public ReportSquadAverageHoursPerDayDto getAverageHoursSquadPerDay(Long squaId, Date initialDate, Date finalDate) throws Exception;
	
	public List<ReportDto> getReportsBySquadBetweenDate(Long squaId, Date initialDate, Date finalDate) throws Exception;
	
}
