package myy803.traineeship_app.strategies.student;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;


import myy803.traineeship_app.domain_model.TraineeshipPosition;
import myy803.traineeship_app.exceptions.PositionNotFoundException;
import myy803.traineeship_app.domain_model.Likes;
import myy803.traineeship_app.mappers.TraineeshipPositionMapper; 
import myy803.traineeship_app.mappers.LikesMapper;



public class SearchBasedOnInterests implements PositionSearchStrategy {

	@Autowired
	private TraineeshipPositionMapper traineeshipMapper;
	
	@Autowired
	private LikesMapper likesMapper;
	
	
	@Override
	public List<TraineeshipPosition> search(String username){
		List<TraineeshipPosition> positions = new ArrayList<>();
		List<Likes> student = likesMapper.findByStudentUsername(username);
		for(Likes like:student) {
			List<TraineeshipPosition> trainTitle = traineeshipMapper.findByTitle(like.getInterest());
			if(!trainTitle.isEmpty()) {
				positions.addAll(trainTitle);
			}
		}	
		
		if(student.isEmpty()) {
			throw new PositionNotFoundException("There are no avaialable positions based on student's interests");
		}else {
			return positions;
		}
		
	}
}
