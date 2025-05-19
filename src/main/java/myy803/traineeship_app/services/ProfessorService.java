package myy803.traineeship_app.services;

import java.util.List;
import java.util.Optional;


import myy803.traineeship_app.domain_model.Professor;
import myy803.traineeship_app.domain_model.TraineeshipPosition;
import myy803.traineeship_app.dtos.ProfessorDto;
import myy803.traineeship_app.dtos.EvaluationDto;
import myy803.traineeship_app.dtos.TraineeshipPositionDto;


public interface ProfessorService {
	
	public ProfessorDto retrieveProfile(String username);
	public void saveProfile(ProfessorDto professor);
	public List<TraineeshipPositionDto> retrieveAssignedPositions(String username);
	EvaluationDto evaluateAssignedPosition(Integer posId);
	void saveEvaluation(EvaluationDto evaluation);
	void deleteProfile(String username);
	void updateProfessorUsername(String username,Integer id);
	
}