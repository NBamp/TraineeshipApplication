package myy803.traineeship_app.mappers;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import myy803.traineeship_app.domain_model.Professor;


public interface ProfessorMapper extends JpaRepository<Professor, String>{
	
	Optional<Professor> findByUsername(String username);
	
	
}