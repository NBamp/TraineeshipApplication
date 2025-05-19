package myy803.traineeship_app.dtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Component
public class CompanyDto{
	
	private String username;
	private String name;
	private String location;
	
	
	@Autowired
	public CompanyDto() {
		
	}
	
	@Override
	public String toString() {
		return "CompanyDto [username=" + username + ", name=" + name + ", location=" + location + "]";
	}
	
	
}