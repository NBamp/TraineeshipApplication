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
@Table(name = "Evaluation")
public class Evaluation{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eval_id")
	private Integer evalId;
	
	@Column(name = "motivation")
	private Integer motivation;
	
	@Column(name = "efficiency")
	private Integer efficiency;
	
	@Column(name = "effectiveness")
	private Integer effectiveness;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "train_pos_id")
	private TraineeshipPosition traineeship;
	
	public Evaluation(TraineeshipPosition traineeship) {
		this.traineeship = traineeship;
	}
	
	@Override
	public String toString() {
		return "Evaluation [eval_id=" + evalId + ", motivation=" + motivation + ", efficiency=" + efficiency
				+ ", effectiveness=" + effectiveness + ", traineeship=" + traineeship + "]";
	}

	
	
	
}