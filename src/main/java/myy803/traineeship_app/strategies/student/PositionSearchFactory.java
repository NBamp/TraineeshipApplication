package myy803.traineeship_app.strategies.student;

import org.springframework.beans.factory.annotation.Autowired;

import myy803.traineeship_app.exceptions.PositionStrategyNotFoundException;

public class PositionSearchFactory {
	
	@Autowired
	private SearchBasedOnInterests searchBasedOnInterests;
	
	@Autowired
	private SearchBasedOnLocation searchBasedOnLocation;
	
	public PositionSearchStrategy create(String strategy) {
		if(strategy.contains("interest")) {
			return searchBasedOnInterests;
		}else if(strategy.contains("location")) {
			return searchBasedOnLocation;
		}else {
			throw new PositionStrategyNotFoundException("Position strategy is not valid , enter again");
		}
	}
}
