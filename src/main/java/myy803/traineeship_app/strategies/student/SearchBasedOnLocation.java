package myy803.traineeship_app.strategies.student;
import myy803.traineeship_app.mappers.CompanyMapper;
import myy803.traineeship_app.mappers.StudentMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import myy803.traineeship_app.domain_model.TraineeshipPosition;
import myy803.traineeship_app.exceptions.PositionNotFoundException;
import myy803.traineeship_app.domain_model.Student;
import myy803.traineeship_app.domain_model.Company;

public class SearchBasedOnLocation implements PositionSearchStrategy {
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private CompanyMapper companyMapper;
	
	
	public List<TraineeshipPosition> search(String username) {
		List<TraineeshipPosition> positions = new ArrayList<>();
		Optional<Student> stud = studentMapper.findByUsername(username);
		if(stud.isPresent() && stud.get().getPreferredLocation()!=null) {
			List<Company> arrayComp = companyMapper.findByLocation(stud.get().getPreferredLocation());
			for(Company comp:arrayComp) {
				positions.addAll(comp.getTraineeshipPos());
			}
		}
		System.out.println(positions);
		if(positions.isEmpty()) {
			throw new PositionNotFoundException("There are no avaialable positions based on student's preffered location");
		}else {
			return positions;
		}
		
	}

}
