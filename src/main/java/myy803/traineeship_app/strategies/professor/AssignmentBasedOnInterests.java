package myy803.traineeship_app.strategies.professor;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import myy803.traineeship_app.mappers.TraineeshipPositionMapper;
import myy803.traineeship_app.mappers.LikesMapper;
import myy803.traineeship_app.domain_model.TraineeshipPosition;
import myy803.traineeship_app.domain_model.Likes;


public class AssignmentBasedOnInterests implements SupervisorAssignmentStrategy{
	
	@Autowired
	private TraineeshipPositionMapper traineeshipMapper;
	
	
	@Autowired
	private LikesMapper likesMapper;
	
	
	@Override
	public void assign(Integer positionId) {
		Optional<TraineeshipPosition> pos = traineeshipMapper.findById(positionId);
		if(pos.isPresent() && pos.get().getSupervisor()==null && pos.get().getTitle()!=null) {
			List<Likes> interest_array = likesMapper.findByInterest(pos.get().getTitle());
			for(Likes like:interest_array) {
				if(like.getProfessor()!=null) {
					traineeshipMapper.updateTraineeshipProfessorUsername(like.getProfessor().getUsername(), positionId);
					likesMapper.updateLikesProfessorUsername(null,like.getInterestId());
				}
			}
			/*if(traineeshipMapper.findById(positionId).get().getSupervisor()==null) {
				throw new SupervisorNotFoundException("No supervisor professor is assigned.");
			}*/
			
		}
	}
	
	

}
