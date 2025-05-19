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
@Table(name = "Likes")
public class Likes{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interest_id")
	private Integer interestId;
	
	@Column(name = "interest")
	private String interest;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "student_username")
	private Student student;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "professor_username")
	private Professor professor;
	
	
	public Likes(String interest,Student student) {
		this.interest = interest;
		this.student = student;
	}
	
	public Likes(String interest,Professor professor) {
		this.interest = interest;
		this.professor = professor;
	}
	
	
}
