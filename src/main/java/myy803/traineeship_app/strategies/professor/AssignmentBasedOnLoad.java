package myy803.traineeship_app.strategies.professor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import myy803.traineeship_app.mappers.TraineeshipPositionMapper;
import myy803.traineeship_app.mappers.ProfessorMapper;
import myy803.traineeship_app.domain_model.Professor;
import myy803.traineeship_app.exceptions.SupervisorNotFoundException;


public class AssignmentBasedOnLoad implements SupervisorAssignmentStrategy {

	@Autowired
	private TraineeshipPositionMapper traineeshipMapper;
	
	@Autowired
	private ProfessorMapper professorMapper;
	
	
	@Override
	public void assign(Integer positionId) {
		List<Professor> prof_array = professorMapper.findAll();
		for(Professor prof:prof_array) {
			if(traineeshipMapper.countBySupervisorUsername(prof.getUsername())<=2 && traineeshipMapper.findById(positionId).isPresent()) {
				traineeshipMapper.updateTraineeshipProfessorUsername(prof.getUsername(), positionId);
			}
		}
		if(traineeshipMapper.findById(positionId).get().getSupervisor().getUsername()==null) {
			throw new SupervisorNotFoundException("No supervisor professor is assigned.");
		}
		
	}
}
