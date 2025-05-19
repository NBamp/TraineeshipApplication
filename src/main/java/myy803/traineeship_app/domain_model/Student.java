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
@Table(name = "student")
public class Student {
	
	@Id
	@Column(name = "st_username")
	private String username;	

	@Column(name="student_name",nullable = false)
	private String  name;
	
	@Column(name="AM",nullable = false)
	private String AM;
	
	@Column(name="avg_grade")
	private Double avgGrade;
	
	@Column(name="preferred_location")
	private String preferredLocation;
	
	@OneToMany(mappedBy = "student",fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Likes>  interests;
	
	@OneToMany(mappedBy = "student",fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Skills> skills;
	
	@Column(name = "looking_for_traineeship")
	private Boolean lookingForTraineeship;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tr_pos_id")
	private TraineeshipPosition traineeship;
	
	public Student() {
		this.interests = new ArrayList<>();
		this.skills = new ArrayList<>();
	}
	
	
	@Override
	public String toString() {
		return "Student [st_username=" + username + ", student_name=" + name + ", AM=" + AM + ", avgGrade="
				+ avgGrade + ", preferredLocation=" + preferredLocation + ", interests=" + interests + ", skills="
				+ skills + ", lookingForTraineeship=" + lookingForTraineeship + ", traineeship=" + traineeship + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
