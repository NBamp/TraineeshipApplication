package myy803.traineeship_app.services;
import myy803.traineeship_app.domain_model.TraineeshipPosition;
import java.util.List;
import myy803.traineeship_app.domain_model.Student;


public interface CommitteeService {

	List<TraineeshipPosition> retrievePositionsForApplicant(String applicantUsername,String strategy);
	List<Student> retrieveTraineeshipApplications();
	void assignPosition(Integer posId,String studentUsername);
	void assignSupervisor(Integer posId,String strategy);
	List<TraineeshipPosition> listAssignedTraineeships();
	void completeAssignedTraineeships(Integer positionId,String grade);
}
