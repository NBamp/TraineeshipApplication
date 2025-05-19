package myy803.traineeship_app.domain_model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Skills")
public class Skills{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "skill_id")
	private Integer skillId;
	
	@Column(name = "skill")
	private String skill;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "student_id")
	private Student student;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "tr_id")
	private TraineeshipPosition traineeshipPos;
	
	public Skills(String skill,Student student) {
		this.skill = skill;
		this.student = student;
	}
	
}