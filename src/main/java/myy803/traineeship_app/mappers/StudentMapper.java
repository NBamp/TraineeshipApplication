package myy803.traineeship_app.mappers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import myy803.traineeship_app.domain_model.Student;




public interface StudentMapper extends JpaRepository<Student, String>{
	
	Optional<Student> findByUsername(String username);
	List<Student> findByLookingForTraineeship(Boolean type);
	Optional<Student> findByTraineeshipTitle(String title);
	@Modifying
	@Transactional
	@Query(value="UPDATE student  SET tr_pos_id = :id WHERE st_username = :username",nativeQuery=true)
	void updateTraineeshipStudent(@Param("id") Integer posId, @Param("username") String  username);
	@Modifying
	@Transactional
	@Query(value="UPDATE student  SET looking_for_traineeship = :True WHERE st_username = :username",nativeQuery=true)
	void updateApplyForStudent(@Param("username") String  username);
	
	
}