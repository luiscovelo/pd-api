package br.com.desafiopd.model.dto;

import java.time.LocalDate;

public interface ReportDto {
	
	String getEmployee();
	String getDescription();
	int getSpentHours();
	LocalDate getCreatedAt();
	
}
