package br.com.desafiopd.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "Não pode ser vazio.")
	private String name;

	@Column(name = "estimated_hours", nullable = false)
	@Range(min = 1, max = 12, message = "O valor deve ser entre 1 e 12.")
	private int estimatedHours;

	@Column(name = "squad_id", nullable = false)
	@Range(min = 1, message = "O valor deve ser maior que zero.")
	private Long squadId;

	public Employee() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(int estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public Long getSquadId() {
		return squadId;
	}

	public void setSquadId(Long squadId) {
		this.squadId = squadId;
	}

}
