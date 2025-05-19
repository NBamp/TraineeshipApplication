package myy803.traineeship_app.dtos;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TraineeshipPositionDto {
	
	private Integer trainId;
	private String title;
	private String description;
	private LocalDate fromDate;
	private LocalDate toDate;
	private String topic;
	private String studentLogBook;
	private Boolean isAssigned;
	private String supervisorUsername;
	private String companyUserName;
	private String studentUsername;
	
	
	@Override
	public String toString() {
		return "TraineeshipPositionDto [description=" + description + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", studentLogBook=" + studentLogBook + ", title=" + title + ", topic=" + topic + "]";
	}


	public TraineeshipPositionDto(String studentUsername,String companyUserName) {
		this.studentUsername= studentUsername;
		this.companyUserName = companyUserName;
	}


	
	
}
