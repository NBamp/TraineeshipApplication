package myy803.traineeship_app.services;

import java.util.List;
import myy803.traineeship_app.domain_model.Company;
import myy803.traineeship_app.domain_model.TraineeshipPosition;
import myy803.traineeship_app.dtos.CompanyDto;
import myy803.traineeship_app.dtos.TraineeshipPositionDto;
import myy803.traineeship_app.dtos.EvaluationDto;


public interface CompanyService{
	
	Company retrieveProfile(String username);
	void saveProfile(CompanyDto company);
	List<TraineeshipPosition> retrieveAvailablePositions(String username);
	void addPosition(TraineeshipPositionDto pos);
	void deletePosition(Integer posId);
	List<TraineeshipPosition> retrieveAssignedPositions(String username);
	EvaluationDto evaluateAssignedPosition(Integer posId);
	void saveEvaluation(EvaluationDto evaluation);
	void deleteEvaluation(Integer posId);
	
	
}