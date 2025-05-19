package myy803.traineeship_app.mappers;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import myy803.traineeship_app.domain_model.TraineeshipPosition;



public interface TraineeshipPositionMapper extends JpaRepository<TraineeshipPosition,Integer>{
	
	Long countBySupervisorUsername(String name);
	List<TraineeshipPosition> findByIsAssigned(boolean type);
	List<TraineeshipPosition> findBySupervisorUsernameAndIsAssigned(String name, boolean type);
	List<TraineeshipPosition> findByCompanyUsernameAndIsAssigned(String name,boolean type);
	Optional<TraineeshipPosition> findByTrainPosIdAndIsAssigned(Integer posId,boolean type);
	List<TraineeshipPosition> findByTitle(String title);
	@Modifying
	@Transactional
	@Query(value="UPDATE traineeship_position  SET proffesor_user = :username WHERE train_pos_id = :id",nativeQuery=true)
	void updateTraineeshipProfessorUsername(@Param("username") String username,@Param("id") Integer id);
	@Modifying
	@Transactional
	@Query(value="UPDATE traineeship_position  SET pass_fail_grade = :grade WHERE train_pos_id = :id",nativeQuery=true)
	void updateTraineeshipPassFailGrade(@Param("grade") String grade,@Param("id") Integer id);
	
	
	
	
	
}