package myy803.traineeship_app.controller;

import java.util.ArrayList;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import myy803.traineeship_app.services.CompanyService;
import org.springframework.web.bind.annotation.PostMapping;
import myy803.traineeship_app.domain_model.TraineeshipPosition;
import myy803.traineeship_app.dtos.CompanyDto;
import myy803.traineeship_app.dtos.EvaluationDto;
import myy803.traineeship_app.dtos.TraineeshipPositionDto;


@RestController
@RequestMapping("/company")
public class CompanyController {

	private static final String PROFILE = "company/profile";
	
	private static final String MESSAGE = "message";
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping("/Dashboard")
	public String getCompanyDashboard() {
		return "company/dashboard";
		
	}
	
	@RequestMapping("/Profile/{id}")
	public String retrieveProfile(@PathVariable("id") String username,Model model) {
		model.addAttribute("retrieve_company",companyService.retrieveProfile(username));
		return PROFILE;
	}
	
	@RequestMapping("/Profile")
	public String createNewProfile(Model model) {
		model.addAttribute("company",new CompanyDto());
		return "company/formCompany";
	}
	
	@PostMapping("/AddProfile")
	public String saveProfile(@ModelAttribute("company")CompanyDto company,Model theModel) {
		companyService.saveProfile(company);
		theModel.addAttribute(MESSAGE,"Company "+ company.getName()+ "saved successfully!");
		return "redirect:/Profile/"+company.getUsername();
	}
	
	@GetMapping("/AvailableList/{id}")
	public String listAvailablePositions(@PathVariable("id") String username,Model model) {
		List<TraineeshipPositionDto> avail = new ArrayList<>();
		List<TraineeshipPosition> positions = companyService.retrieveAvailablePositions(username);
		for(TraineeshipPosition pos:positions) {
			avail.add(modelMapper.map(pos, TraineeshipPositionDto.class));
		}
		model.addAttribute("available_list",avail);
		return "company/available";
	}
	
	@GetMapping("/AssignedList/{id}")
	public String listAssignedPositions(@PathVariable("id") String username,Model model) {
		Transform(model, companyService.retrieveAssignedPositions(username), modelMapper, username);
		return "company/assigned";
	}

	static void Transform(Model model, List<TraineeshipPosition> traineeshipPositions, ModelMapper modelMapper, @PathVariable("id") String username) {
		List<TraineeshipPositionDto> assign = new ArrayList<>();
		for(TraineeshipPosition pos:traineeshipPositions) {
			assign.add(modelMapper.map(pos, TraineeshipPositionDto.class));
		}
		model.addAttribute("assigned_list",assign);
	}

	@GetMapping("/FormPosition/{id}")
	public String showPositionForm(@PathVariable("id") String username,Model model) {
		model.addAttribute("position",new TraineeshipPositionDto(username,""));
		return "company/formPosition";
	}
	
	@PostMapping("/AddPosition")
	public String savePosition(@ModelAttribute("position") TraineeshipPositionDto pos,Model model) {
		pos.setCompanyUserName("Karolos");
		pos.setTitle("System");
		companyService.addPosition(pos);
		model.addAttribute(MESSAGE,"Position with "+ pos.getTitle() +"is saved");
		return PROFILE;
	}
	
	@GetMapping("/Evaluate/{id}")
	public String evaluateAssignedTraineeship(@PathVariable("id") Integer posId,String username,Model model) {
		model.addAttribute("evaluation",companyService.evaluateAssignedPosition(posId));
		return "company/formEvaluate";
	}
	
	@PostMapping("/AddEvaluation/{id}")
	public String saveEvaluation(@ModelAttribute("evaluation") EvaluationDto eval,@PathVariable("id") String username,Model model) {
		companyService.saveEvaluation(eval);
		model.addAttribute(MESSAGE, "Evaluation is saved");
		return "redirect:AssignedList/"+username;
	}
	
	@DeleteMapping("/DeletePosition/{id}")
	public String deletePosition(@PathVariable("id") Integer posId,Model model) {
		companyService.deletePosition(posId);
		model.addAttribute(MESSAGE,"Traineeship position is deleted");
		return PROFILE;
	}
	
	@DeleteMapping("/DeleteEvaluation/{id}")
	public String deleteEvaluation(@PathVariable("id") Integer posId,Model model) {
		companyService.deleteEvaluation(posId);
		model.addAttribute(MESSAGE,"Evaluation is deleted");
		return PROFILE;
	}
	
	
	
	
}
