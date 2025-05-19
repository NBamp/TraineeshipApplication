package myy803.traineeship_app.domain_model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter

@AllArgsConstructor
@Entity
@Table(name = "Company")
public class Company{
	
	@Id
	@Column(name = "comp_username")
	private String username;
	
	@Column(name = "name",nullable = false)
	private String name;
	
	@Column(name = "Location",nullable = false)
	private String location;
	
	@OneToMany(mappedBy = "company",fetch = FetchType.EAGER)
	private List<TraineeshipPosition>  traineeshipPos;
	
	public Company() {
		this.traineeshipPos = new ArrayList<>();
	}
	
	
	@Override
	public String toString() {
		return "Company [comp_username=" + username + ", name=" + name + ", Location="
				+ location + "]";
	}
	
	
	
}