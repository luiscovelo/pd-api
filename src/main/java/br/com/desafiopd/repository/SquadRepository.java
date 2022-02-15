package br.com.desafiopd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.desafiopd.model.entities.Squad;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Long> {
	
	@Query(nativeQuery = true, value = "select count(*) from employee where squad_id = :squad_id")
	public int getCountEmployees(@Param("squad_id") Long id);
	
}
