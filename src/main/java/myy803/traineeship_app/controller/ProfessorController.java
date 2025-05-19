package myy803.traineeship_app.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import myy803.traineeship_app.services.ProfessorService;
import org.springframework.web.bind.annotation.PostMapping;
import myy803.traineeship_app.dtos.ProfessorDto;
import myy803.traineeship_app.dtos.EvaluationDto;




@RestController
@RequestMapping("/professor")
public class ProfessorController{
	

	private static final String VIEW = "professor/dashboard";

	private static final String MESSAGE = "message";
	
	private static final String PROFILE = "professor/profile";

	private static final String LIST = "professor/assigned";


	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping("/Dashboard")
	public String getDashboard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("username",authentication.getName());
		return VIEW;
	}
	
	@GetMapping("/Profile")
	public String retrieveProfile(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("retrieve_professor",professorService.retrieveProfile(authentication.getName()));
		return PROFILE;
	
	}
	
	@GetMapping("/AddProfile")
	public String createNewProfile(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("professor",new ProfessorDto(authentication.getName()));
		return "professor/formProfessor";
	}
	
	@PostMapping("/SaveProfile")
	public String saveProfile(@ModelAttribute("professor")ProfessorDto professor,Model theModel) {
		professorService.saveProfile(professor);
		theModel.addAttribute("username",professor.getUsername());
		theModel.addAttribute("flag","True");
		return VIEW;
	}
	
	@GetMapping("/AssignedList")
	public String listAssignedPositions(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("assigned_list",professorService.retrieveAssignedPositions(authentication.getName()));
		return LIST;
	}
	
	@GetMapping("/Evaluate/{id}")
	public String evaluateAssignedTraineeship(@PathVariable("id") Integer posId,Model model) {
		model.addAttribute("evaluation",professorService.evaluateAssignedPosition(posId));
		return "professor/formEvaluate";
	}
	
	@PostMapping("/AddEvaluation")
	public String saveEvaluation(@ModelAttribute("evaluation") EvaluationDto eval,Model model) {
		professorService.saveEvaluation(eval);
		model.addAttribute(MESSAGE, "Evaluation is saved");
		return LIST;
	}
			
	@DeleteMapping("/Delete")
	public String deleteProfile(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		professorService.deleteProfile(authentication.getName());
		model.addAttribute(MESSAGE, "Profile is deleted");
		return PROFILE;
		
	}
	
	
}