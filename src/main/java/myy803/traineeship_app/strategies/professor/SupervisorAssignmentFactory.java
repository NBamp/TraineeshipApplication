package myy803.traineeship_app.strategies.professor;

import org.springframework.beans.factory.annotation.Autowired;

import myy803.traineeship_app.exceptions.SupervisorStrategyNotFoundException;

public class SupervisorAssignmentFactory {
	
	@Autowired
	private AssignmentBasedOnInterests assignmentBasedOnInterests;
	
	@Autowired
	private AssignmentBasedOnLoad assignmentBasedOnLoad;
	
	
	public SupervisorAssignmentStrategy create(String strategy) {
		if(strategy.contains("interest")) {
			return assignmentBasedOnInterests;
		}else if(strategy.contains("load")) {
			return assignmentBasedOnLoad;
		}else {
			throw new SupervisorStrategyNotFoundException("Supervisor strategy is not valid , enter again");
		}
	}
	
	
}