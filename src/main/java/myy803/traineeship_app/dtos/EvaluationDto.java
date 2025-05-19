package myy803.traineeship_app.dtos;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
public class EvaluationDto {

	private int motivation;
	private int efficiency;
	private int effectiveness;
	private Integer trainId;
	
	
	public EvaluationDto(Integer trainId) {
		this.trainId = trainId;
	}
	
	
	
	
}
