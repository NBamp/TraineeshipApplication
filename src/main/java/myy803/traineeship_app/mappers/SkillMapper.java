package myy803.traineeship_app.mappers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import myy803.traineeship_app.domain_model.Skills;
import myy803.traineeship_app.domain_model.TraineeshipPosition;



public interface SkillMapper extends JpaRepository<Skills, Integer>{
	
	List<Skills> findByStudentName(String name);
	List<Skills> findByTraineeshipPos(TraineeshipPosition pos);
	
}