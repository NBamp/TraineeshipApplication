package myy803.traineeship_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import myy803.traineeship_app.domain_model.Student;
import myy803.traineeship_app.domain_model.TraineeshipPosition;
import myy803.traineeship_app.services.CommitteeService;
import myy803.traineeship_app.dtos.StudentDto;
import myy803.traineeship_app.dtos.TraineeshipPositionDto;



@RestController
@RequestMapping("/committee")
public class CommitteeController {
	
	private static final String PROFILE = "committee/traineeship_applications";
	
	private static final String MESSAGE = "message";
	
	
	@Autowired
	private CommitteeService committeeService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/Dashboard")
	public String getCommitteeDashboard() {
		return "committee/dashboard";
	}
	
	@GetMapping("/StudentsApplications")
	public String listTraineeshipApplications(Model model) {
		List<StudentDto> studs = new ArrayList<>();
		for(Student stud : committeeService.retrieveTraineeshipApplications()) {
			studs.add(modelMapper.map(stud, StudentDto.class));
		}
		model.addAttribute("students_list",studs);
		return PROFILE;
	}
	
	@GetMapping("/Position/{id}/{strategy}")
	public String findPositions(@PathVariable("id") String username,@PathVariable("strategy") String strategy,Model model) {
			List<TraineeshipPositionDto> pos = new ArrayList<>();
		for(TraineeshipPosition posit : committeeService.retrievePositionsForApplicant(username, strategy)) {
			pos.add(modelMapper.map(posit, TraineeshipPositionDto.class));
		}
		model.addAttribute("positions_list",pos);
		model.addAttribute("id",username);
		return "committee/traineeship_positions_student";
		
	}
	
	@PostMapping("AssignPosition/{id}/{username}")
	public String assignPosition(@PathVariable("id") Integer id,@PathVariable("username") String username,Model model) {
		committeeService.assignPosition(id, username);
		model.addAttribute(MESSAGE,"Position is assigned to student"+username);
		return PROFILE;
	}
	
	@PostMapping("AssignSupervisor/{id}/{strategy}")
	public String assignSupervisor(@PathVariable("id") Integer id,@PathVariable("strategy") String strategy,Model model) {
		committeeService.assignSupervisor(id, strategy);
		model.addAttribute(MESSAGE,"A supervisor is assigned to position");
		return PROFILE;
	}
	
	@GetMapping("AssignedTraineeships")
	public String listAssignedTraineeships(Model model) {
		List<TraineeshipPositionDto> pos = new ArrayList<>();
		for(TraineeshipPosition posit : committeeService.listAssignedTraineeships()) {
			pos.add(modelMapper.map(posit, TraineeshipPositionDto.class));
		}
		model.addAttribute("assigned_positions_list",pos);
		return "committee/assigned_traineeships";
	}
	
	@PostMapping("AssignEvaluations/{id}/{Grade}")
	public String completeAssignedTraineeships(@PathVariable("id") Integer id,@PathVariable("Grade") String grade,Model model) {
		committeeService.completeAssignedTraineeships(id,grade);
		model.addAttribute(MESSAGE,"Evaluations are assigned");
		return "committee/assigned_traineeships";
	}
	
}
