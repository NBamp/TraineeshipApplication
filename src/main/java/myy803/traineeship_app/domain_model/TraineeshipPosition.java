package myy803.traineeship_app.domain_model;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "traineeship_position")
public class TraineeshipPosition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "train_pos_id")
	private Integer trainPosId;
	
	@Column(name = "title",nullable = false)
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "from_date",nullable = false)
	private LocalDate fromDate;
	
	@Column(name = "to_date",nullable = false)
	private LocalDate toDate;
	
	@Column(name = "topic",nullable = false)
	private String topic;
	
	@OneToMany(mappedBy = "traineeshipPos",fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Skills> skills;
	
	@Column(name = "is_assigned")
	private Boolean isAssigned;
	
	@Column(name = "student_log_book")
	private String studentLogBook;
	
	@Column(name = "pass_fail_grade")
	private String passFailGrade;
	
	@OneToOne(mappedBy = "traineeship")
	private Student student;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "professor_user")
	private Professor supervisor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_user")
	private Company company;
	
	@OneToMany(mappedBy = "traineeship",fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Evaluation>  evaluations;
	
	public TraineeshipPosition() {
		this.evaluations = new ArrayList<>();
		
	}
	
	public TraineeshipPosition(String title,Company company) {
		this.title = title;
		this.company = company;
	}
	

}
