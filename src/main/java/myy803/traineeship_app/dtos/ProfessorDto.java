package myy803.traineeship_app.dtos;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProfessorDto{
	
	private String username;
	private String name;
	private List<String> interests;
	
	
	
	public ProfessorDto() {
		this.interests = new ArrayList<>();
	}
	
	public ProfessorDto(String username) {
		this.username = username;
		this.interests = new ArrayList<>();
		
	}
	
	@Override
	public String toString() {
		return "ProfessorDto [username=" + username + ", name=" + name + "]";
	}


	
	
}