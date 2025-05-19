	package myy803.traineeship_app.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.traineeship_app.domain_model.Student;
import myy803.traineeship_app.domain_model.TraineeshipPosition;
import myy803.traineeship_app.exceptions.StudentNotFoundException;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionMapper;
import myy803.traineeship_app.strategies.student.*;
import myy803.traineeship_app.strategies.professor.*;



@Service
public class ICommitteeService implements CommitteeService {
	
	@Autowired
	private PositionSearchFactory posStrategy;
	
	@Autowired
	private SupervisorAssignmentFactory supStrategy;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private TraineeshipPositionMapper traineeshipMapper;
	
	@Override
	public List<TraineeshipPosition> retrievePositionsForApplicant(String username,String strategy){
		if(studentMapper.findByUsername(username).isPresent()) {
			return posStrategy.create(strategy).search(username);
		}else {
			throw new StudentNotFoundException("Student with username "+ username + "not found");
		}
	}
	
	@Override
	public List<Student> retrieveTraineeshipApplications(){
		return studentMapper.findByLookingForTraineeship(true);
	}
	
	@Override
	public void assignPosition(Integer posId,String studentUsername) {
		if(traineeshipMapper.findById(posId).isPresent() && studentMapper.findByUsername(studentUsername).isPresent()) {
			studentMapper.updateTraineeshipStudent(posId, studentUsername);
		}
	}
	
	@Override
	public void assignSupervisor(Integer posId,String strategy) {
		supStrategy.create(strategy).assign(posId);  
	}
	
	@Override
	public List<TraineeshipPosition> listAssignedTraineeships(){
		return traineeshipMapper.findByIsAssigned(true);
	}
	
	@Override
	public void completeAssignedTraineeships(Integer positionId,String grade) {
		if(traineeshipMapper.findByTrainPosIdAndIsAssigned(positionId, true).isPresent()){
			traineeshipMapper.updateTraineeshipPassFailGrade(grade, positionId);
		}
	}
	
	
}
