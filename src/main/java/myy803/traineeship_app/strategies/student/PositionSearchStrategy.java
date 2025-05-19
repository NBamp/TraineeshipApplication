package myy803.traineeship_app.strategies.student;
import java.util.List;

import myy803.traineeship_app.domain_model.TraineeshipPosition;

public interface PositionSearchStrategy {
	
	List<TraineeshipPosition> search(String username);
}
