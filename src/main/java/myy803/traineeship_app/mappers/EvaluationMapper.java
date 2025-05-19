package myy803.traineeship_app.mappers;


import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import myy803.traineeship_app.domain_model.Evaluation;

public interface EvaluationMapper extends JpaRepository<Evaluation,Integer>{
	
	long countByTraineeshipTrainPosId(Integer x);
	Optional<Evaluation> findByTraineeshipTrainPosId(Integer id);
	Optional<Evaluation> findByEvalIdAndTraineeshipTrainPosId(Integer id,Integer tId);
	void deleteByEvalId(Integer x);
	
	
}