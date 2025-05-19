package myy803.traineeship_app.services;



import java.util.ListIterator;
import java.util.Optional;

import myy803.traineeship_app.domain_model.Student;
import myy803.traineeship_app.domain_model.TraineeshipPosition;
import myy803.traineeship_app.domain_model.Skills;
import myy803.traineeship_app.domain_model.Likes;
import myy803.traineeship_app.dtos.StudentDto;
import myy803.traineeship_app.dtos.TraineeshipPositionDto;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionMapper;
import myy803.traineeship_app.mappers.LikesMapper;
import myy803.traineeship_app.mappers.SkillMapper;
import myy803.traineeship_app.exceptions.StudentNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class IStudentService implements StudentService{
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private TraineeshipPositionMapper traineeshipMapper;
	
	@Autowired
	private SkillMapper skillMapper;
	
	@Autowired
	private LikesMapper likesMapper;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public IStudentService(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		
		
	}
	
	PropertyMap<StudentDto, Student> skipModifiedFieldsMap1 = new PropertyMap<StudentDto, Student>() {
	      protected void configure() {
	    	 skip().setSkills(null);
	         skip().setInterests(null);
	     }
	   };
	   
	PropertyMap<TraineeshipPositionDto, TraineeshipPosition> skipModifiedFieldsMap2 = new PropertyMap<TraineeshipPositionDto, TraineeshipPosition>() {
		  protected void configure() {
			 skip().setStudent(null);
		     skip().setCompany(null);
		     skip().setSupervisor(null);
		     skip().setEvaluations(null);
		     
		  }
	};


	@Override
	public void saveProfile(StudentDto student){
		if(student.getStudentUsername()!= null) {
			Student st = modelMapper.map(student,Student.class);
			st.setTraineeship(null);
			addSkillsAndInterests(student,st);
			studentMapper.save(st);
			
		}
		
	}
	
	
	private void addSkillsAndInterests(StudentDto student,Student stud) {
		if(!student.getSkills().isEmpty()) {
			for(String skill : student.getSkills()) {
				skillMapper.save(new Skills(skill,stud));
			}
		}
		if(!student.getInterests().isEmpty()) {
			for(String interest: student.getInterests()) {
				likesMapper.save(new Likes(interest,stud));
			}
		}
			
		
	}
	private void addSkillsAndInterests(Student student,StudentDto stud) {
		if(!student.getSkills().isEmpty()) {
			for(Skills skill : student.getSkills()) {
				stud.getSkills().add(skill.getSkill());
			}
		}
		if(!student.getInterests().isEmpty()) {
			for(Likes interest: student.getInterests()) {
				stud.getInterests().add(interest.getInterest());
			}
		}


	}
	
	@Override
	public void deleteProfile(String username) {
		studentMapper.findByUsername(username).ifPresent(studentMapper::delete);
		
	}

	@Override
	public void applyForTraineeship(String username) {
		if(studentMapper.findByUsername(username).isPresent()) {
			studentMapper.updateApplyForStudent(username);
		}


	}
	@Override
	public StudentDto retrieveProfile(String username) {
		Optional<Student> stud = studentMapper.findByUsername(username);
		if(stud.isPresent()) {
			StudentDto student = modelMapper.map(stud.get(),StudentDto.class);
			addSkillsAndInterests(stud.get(),student);
			return student;
		}
		throw new StudentNotFoundException("Student with username " + username + " not found");

	}

	@Override
	public void saveLogBook(TraineeshipPositionDto pos,String username) {
		Optional<Student> st = studentMapper.findByUsername(username);
		if(st.isPresent() && pos.getTitle()!=null) {
			TraineeshipPosition p  = modelMapper.map(pos, TraineeshipPosition.class);
			p.setStudent(st.get());
			traineeshipMapper.save(p);
		}
	}
}