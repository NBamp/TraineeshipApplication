package myy803.traineeship_app.services;

import java.util.Optional;
import java.util.List;

import java.util.ArrayList;
import myy803.traineeship_app.domain_model.Company;
import myy803.traineeship_app.domain_model.TraineeshipPosition;
import myy803.traineeship_app.domain_model.Evaluation;
import myy803.traineeship_app.dtos.CompanyDto;
import myy803.traineeship_app.dtos.TraineeshipPositionDto;
import myy803.traineeship_app.dtos.EvaluationDto;
import myy803.traineeship_app.mappers.CompanyMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionMapper;
import myy803.traineeship_app.mappers.EvaluationMapper;
import myy803.traineeship_app.exceptions.CompanyNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;





@Service
public class ICompanyService implements CompanyService{
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired 
	private TraineeshipPositionMapper traineeshipMapper;
	
	@Autowired
	private EvaluationMapper evaluationMapper;
	
	private boolean type;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	public ICompanyService(ModelMapper modelMapper){
		this.modelMapper = modelMapper;
	}
	
	
	
	PropertyMap<TraineeshipPositionDto, TraineeshipPosition> skipModifiedFieldsMap1 = new PropertyMap<TraineeshipPositionDto, TraineeshipPosition>() {
		  protected void configure() {
			 skip().setSkills(null);
			 skip().setStudent(null);
		     skip().setCompany(null);
		     skip().setSupervisor(null);
		     skip().setEvaluations(null);
		     
		  }
	};
	
	PropertyMap<EvaluationDto, Evaluation> skipModifiedFieldsMap2 = new PropertyMap<EvaluationDto,Evaluation>() {
		  protected void configure() {
			 skip().setEvalId(null);
		     skip().setTraineeship(null);
		  
		  }
	};
	
	@Override
	public Company retrieveProfile(String username) {
		return companyMapper.findByUsername(username)
				.orElseThrow(()-> new CompanyNotFoundException("Company Profile with username "+username+" doesnt exist!"));
	}
	
	@Override
	public void saveProfile(CompanyDto company) {
		if(company.getUsername()!=null) {
			Company comp = modelMapper.map(company,Company.class);
			companyMapper.save(comp);
		}else {
			throw new CompanyNotFoundException("Company Profile with username doesnt exist!");
		}
	}
	
	
		
	
	@Override
	public List<TraineeshipPosition> retrieveAvailablePositions(String username){
		Optional<Company> comp = companyMapper.findByUsername(username);
		if(comp.isPresent()) {
			return traineeshipMapper.findByCompanyUsernameAndIsAssigned(comp.get().getUsername(),false);
		}
		return new ArrayList<>();
		
	}
	
	@Override
	public List<TraineeshipPosition> retrieveAssignedPositions(String username){
		Optional<Company> comp = companyMapper.findByUsername(username);
		if(comp.isPresent()) 
			{return traineeshipMapper.findByCompanyUsernameAndIsAssigned(comp.get().getUsername(),true);}
		return new ArrayList<>();
	}
	
	
	@Override
	public void addPosition(TraineeshipPositionDto pos) {
		Optional<Company> comp = companyMapper.findByUsername(pos.getCompanyUserName());
		if(comp.isPresent() && pos.getTitle()!=null) {
			TraineeshipPosition position = modelMapper.map(pos,TraineeshipPosition.class);
			position.setCompany(comp.get());
			traineeshipMapper.save(position);
		}
		else {
			throw new CompanyNotFoundException("Company Profile with username "+pos.getCompanyUserName()+" doesnt exist" );
		}
		
		
	}
	
	@Override
	public void deletePosition(Integer posId) {
		traineeshipMapper.findById(posId).ifPresent(traineeshipMapper::delete);
	}
	
	
	@Override
	public EvaluationDto evaluateAssignedPosition(Integer posId) {
		if(traineeshipMapper.findByTrainPosIdAndIsAssigned(posId, true).isPresent() && evaluationMapper.countByTraineeshipTrainPosId(posId)<=1 && !type) {
			type = true;
			return new EvaluationDto(posId);
		}else if(type) {
			throw new CompanyNotFoundException("Cannot make another evaluation");
		}
		throw new CompanyNotFoundException("Assigned traineeship position is not valid");
	}
	
	
	@Override
	public void saveEvaluation(EvaluationDto evaluation) {
		Optional<TraineeshipPosition> pos = traineeshipMapper.findById(evaluation.getTrainId());
		if(pos.isPresent()) {
			Evaluation eval = modelMapper.map(evaluation, Evaluation.class);
			eval.setTraineeship(pos.get());
			evaluationMapper.save(eval);
		}else {
			throw new CompanyNotFoundException("Assigned traineeship position is not valid");
		}
	}
	
	
	
	@Override
	public void deleteEvaluation(Integer posId) {
		Optional<Evaluation> eval = evaluationMapper.findByTraineeshipTrainPosId(posId);
        eval.ifPresent(evaluation -> evaluationMapper.deleteByEvalId(evaluation.getEvalId()));
	}
}
