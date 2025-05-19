package myy803.traineeship_app.dtos;

import org.springframework.stereotype.Component;

import lombok.Data;

import java.util.List;
import java.util.ArrayList;
import myy803.traineeship_app.domain_model.Student;


@Data
@Component
public class StudentDto{
	
	private String studentUsername;
	private String studentName;
	private String AM;
	private Double avgGrade;
	private String preferredLocation;
	private Boolean lookingForTraineeship;
	private List<String> skills;
	private List<String> interests;
	
	


	public StudentDto() {
		
		this.skills = new ArrayList<>();
		this.interests = new ArrayList<>();
		
	}

	public StudentDto(String username) {
		this.studentUsername = username;
		this.skills = new ArrayList<>();
		this.interests = new ArrayList<>();
	}

	
	@Override
	public String toString() {
		return "StudentDto [username=" + studentUsername + ", name=" + studentName + ", AM=" + AM + ", avgGrade=" + avgGrade
				+ ", preferredLocation=" + preferredLocation + "]";
	}

}