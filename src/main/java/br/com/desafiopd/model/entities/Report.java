package br.com.desafiopd.model.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "report")
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank(message = "O valor não pode ser vazio.")
	private String description;
	
	@Column(name = "employee_id")
	@Range(min = 1, message = "O valor deve ser maior que zero.")
	@NotNull(message = "O valor não pode ser nulo.")
	private Long employeeId;
	
	@Column(name = "spent_hours")
	@Range(min = 1, message = "O valor deve ser maior que zero.")
	@NotNull(message = "O valor não pode ser nulo.")
	private int spentHours;
	
	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDate createdAt;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "id", referencedColumnName = "employee_id")
	private Employee employee;
	
	public Report() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public int getSpentHours() {
		return spentHours;
	}

	public void setSpentHours(int spentHours) {
		this.spentHours = spentHours;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

}
