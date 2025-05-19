package myy803.traineeship_app.services;


import java.util.List;
import java.util.Optional;
import myy803.traineeship_app.domain_model.Student;
import myy803.traineeship_app.domain_model.TraineeshipPosition;
import myy803.traineeship_app.dtos.StudentDto;
import myy803.traineeship_app.dtos.TraineeshipPositionDto;


public interface StudentService {
	
	void saveProfile(StudentDto student);
	void deleteProfile(String username);
	StudentDto retrieveProfile(String username);
	void saveLogBook(TraineeshipPositionDto pos,String username);
	void applyForTraineeship(String username);
	
	
}