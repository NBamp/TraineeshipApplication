package myy803.traineeship_app.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import myy803.traineeship_app.domain_model.*;
import myy803.traineeship_app.dtos.EvaluationDto;
import myy803.traineeship_app.dtos.ProfessorDto;
import myy803.traineeship_app.dtos.TraineeshipPositionDto;
import myy803.traineeship_app.mappers.ProfessorMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionMapper;
import myy803.traineeship_app.mappers.EvaluationMapper;
import myy803.traineeship_app.mappers.LikesMapper;
import myy803.traineeship_app.exceptions.ProfessorNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;




@Service
public class IProfessorService  implements ProfessorService{
	
	@Autowired
	private ProfessorMapper professorMapper;
	
	@Autowired
	private TraineeshipPositionMapper traineeshipMapper;
	
	@Autowired 
	private EvaluationMapper evaluationMapper;
	
	@Autowired
	private LikesMapper likesMapper;
	
	private  boolean type;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public IProfessorService(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	PropertyMap<ProfessorDto, Professor> skipModifiedFieldsMap2 = new PropertyMap<ProfessorDto, Professor>() {
		  protected void configure() {
			 skip().setInterests(null);
		     skip().setTraineeshipPos(null);
		    
		     
		  }
	};
	private void addInterests(Professor professor,ProfessorDto prof) {
		if(!professor.getInterests().isEmpty()) {
			for(Likes interest: professor.getInterests()) {
				prof.getInterests().add(interest.getInterest());
			}
		}


	}
	
	@Override
	public ProfessorDto retrieveProfile(String username) {
		Optional<Professor> professor = professorMapper.findByUsername(username);
		if(professor.isPresent()) {
			ProfessorDto prof = modelMapper.map(professor.get(),ProfessorDto.class);
			addInterests(professor.get(),prof);
			return prof;
		}
		throw new ProfessorNotFoundException("Professor with username " + username + " not found");
	}
	
	@Override
	public void saveProfile(ProfessorDto professor) {
		if(professor.getUsername()!=null) {
			Professor pr  = modelMapper.map(professor, Professor.class);
			addInterests(professor,pr);
			professorMapper.save(pr);
		}else {
			throw new ProfessorNotFoundException("Professor with username is not valid");
		}
	}
	
	private void addInterests(ProfessorDto professor,Professor prof) {
		if(!professor.getInterests().isEmpty()) {
			for(String interest: professor.getInterests()) {
				likesMapper.save(new Likes(interest,prof));
			}
		}
		
	}
	
	@Override
	public List<TraineeshipPositionDto> retrieveAssignedPositions(String username){
		Optional<Professor> prof = professorMapper.findByUsername(username);
		ArrayList<TraineeshipPositionDto> positions = new ArrayList<>();
		if(prof.isPresent() && !traineeshipMapper.findBySupervisorUsernameAndIsAssigned(prof.get().getUsername(),true).isEmpty()) {
			for(TraineeshipPosition train: traineeshipMapper.findBySupervisorUsernameAndIsAssigned(prof.get().getUsername(),true)){
				positions.add(modelMapper.map(train,
						TraineeshipPositionDto.class));
			}

		}
		return positions;
	}
	
	@Override
	public EvaluationDto evaluateAssignedPosition(Integer posId) {
		if(traineeshipMapper.findByTrainPosIdAndIsAssigned(posId, true).isPresent()) {
			return new EvaluationDto(posId);
		}
		throw new ProfessorNotFoundException("Assigned traineeship position is not valid");
	}
	
	
	@Override
	public void saveEvaluation(EvaluationDto evaluation) {
		Optional<TraineeshipPosition> pos = traineeshipMapper.findById(evaluation.getTrainId());
		if(pos.isPresent()) {
			Evaluation eval = modelMapper.map(evaluation, Evaluation.class);
			eval.setTraineeship(pos.get());
			evaluationMapper.save(eval);
		}else {
			throw new ProfessorNotFoundException("Assigned traineeship position is not valid");
		}
	}
	
		
	@Override
	public void deleteProfile(String username) {
		professorMapper.findById(username).ifPresent(professorMapper::delete);
		
	}
		
	@Override
	public void updateProfessorUsername(String username,Integer id) {
		likesMapper.updateLikesProfessorUsername(username, id);
	}
	
	
	
}

