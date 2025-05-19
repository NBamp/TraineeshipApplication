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
@Table(name = "Professor")
public class Professor{
	
	
	@Id
	@Column(name = "pr_username")
	private String username;
	
	@Column(name = "name",nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "professor",fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Likes>  interests;
	
	@OneToMany(mappedBy = "supervisor",fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<TraineeshipPosition>  traineeshipPos;
	
	public Professor() {
		this.interests = new ArrayList<>();
		this.traineeshipPos = new ArrayList<>();
	}
	
	
	
	
	
}