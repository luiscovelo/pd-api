package br.com.desafiopd.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.desafiopd.model.dto.ReportDto;
import br.com.desafiopd.model.dto.ReportEmployeeHourDto;
import br.com.desafiopd.model.dto.ReportSquadAverageHoursPerDayDto;
import br.com.desafiopd.model.dto.ReportSquadHourDto;
import br.com.desafiopd.model.entities.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>{
	
	@Query(
			nativeQuery = true, 
			value = "select "
					+ "	emp.name as employee, "
					+ "	sum(rep.spent_hours) as hours "
					+ "from report rep "
					+ "join employee emp on emp.id = rep.employee_id "
					+ "join squad sq on sq.id = emp.squad_id "
					+ "where sq.id = :squad_id "
					+ "and date(rep.created_at) between :initial_date and :final_date "
					+ "group by emp.id "
					+ "order by hours desc"
	)
	public List<ReportEmployeeHourDto> getTotalHoursSquadMember(@Param("squad_id") Long squadId, @Param("initial_date") Date initialDate, @Param("final_date") Date finalDate);
	
	@Query(
			nativeQuery = true, 
			value = "select "
					+ "	sq.name as squad, "
					+ "	sum(rep.spent_hours) as hours "
					+ "from report rep "
					+ "join employee emp on emp.id = rep.employee_id "
					+ "join squad sq on sq.id = emp.squad_id "
					+ "where sq.id = :squad_id "
					+ "and date(rep.created_at) between :initial_date and :final_date "
					+ "group by sq.id "
					+ "order by hours desc"
	)
	public ReportSquadHourDto getTotalHoursSquad(@Param("squad_id") Long squadId, @Param("initial_date") Date initialDate, @Param("final_date") Date finalDate);
	
	@Query(
			nativeQuery = true, 
			value = "select "
					+ "	avg(rep.spent_hours) as hours "
					+ "from report rep "
					+ "join employee emp on emp.id = rep.employee_id "
					+ "join squad sq on sq.id = emp.squad_id "
					+ "where sq.id = :squad_id "
					+ "and date(rep.created_at) between :initial_date and :final_date "
	)
	public ReportSquadAverageHoursPerDayDto getAverageHoursSquadPerDate(@Param("squad_id") Long squadId, @Param("initial_date") Date initialDate, @Param("final_date") Date finalDate);
	
	@Query(
			nativeQuery = true,
			value = "select "
					+ " e.name as employee, "
					+ " r.description as description, "
					+ " r.spent_hours as spentHours, "
					+ " r.created_at as createdAt "
					+ "from report r "
					+ "join employee e on e.id = r.employee_id "
					+ "where e.squad_id = :squad_id "
					+ "and date(r.created_at) between :initial_date and :final_date "
	)
	public List<ReportDto> getReportsBySquadBetweenDate(@Param("squad_id") Long squadId, @Param("initial_date") Date initialDate, @Param("final_date") Date finalDate);
	
}
