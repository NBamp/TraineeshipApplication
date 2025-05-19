package myy803.traineeship_app.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import myy803.traineeship_app.services.StudentService;
import myy803.traineeship_app.dtos.StudentDto;
import myy803.traineeship_app.dtos.TraineeshipPositionDto;






@Controller
@RequestMapping("/student")
public class StudentController{
	
	private static final String MESSAGE = "MESSAGE";
	
	private static final String VIEW = "student/dashboard";
	
	private static final String PROFILE = "student/profile";
	
	@Autowired 
	private StudentService studentService;
	
	
	@GetMapping("/Dashboard")
	public String getStudentDashboard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("username",authentication.getName());
		return VIEW;
	}
	
	
	@GetMapping("/Profile")
	public String retrieveProfile(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("retrieve_student",studentService.retrieveProfile(authentication.getName()));
		return PROFILE;
	}
	
	@GetMapping("/AddProfile")
	public String createNewProfile(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("student",new StudentDto(authentication.getName()));
		return "student/formStudent";
	}
	

	@PostMapping("/SaveProfile")
	public String saveProfile(@ModelAttribute("student") StudentDto student,Model theModel) {
		studentService.saveProfile(student);
		theModel.addAttribute("username",student.getStudentUsername());
		theModel.addAttribute("flag","True");
		theModel.addAttribute(MESSAGE,"Profile was successfully created");
		return VIEW;
	}
	
	
	@DeleteMapping("/DeleteProfile")
	public String deleteProfile(@ModelAttribute("student")StudentDto student,Model theModel) {
		studentService.deleteProfile(student.getStudentUsername());
		theModel.addAttribute(MESSAGE,"Student "+ student.getStudentName() +" is deleted");
		return VIEW;
		
	}

	@PostMapping("/ApplyForTraineeship")
	public String applyForTraineeship(@ModelAttribute("retrieve_student")StudentDto student,Model model) {
		studentService.applyForTraineeship(student.getStudentUsername());
		model.addAttribute("retrieve_student",student);
		model.addAttribute(MESSAGE,"Application for Traineeship added successfully!");
		return PROFILE;
	}
	
	@GetMapping("/LogBook")
	public String fillLogBook(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		TraineeshipPositionDto position = new TraineeshipPositionDto(authentication.getName(), "");
		model.addAttribute("position", position);
		return PROFILE;
		
	}


	@PostMapping("/AddLogBook")
	public String saveLogBook(@ModelAttribute("position") TraineeshipPositionDto position,Model theModel) {
		studentService.saveLogBook(position,position.getStudentUsername());
		theModel.addAttribute(MESSAGE,"LogBook was successfully saved");
		return VIEW;
	}
}