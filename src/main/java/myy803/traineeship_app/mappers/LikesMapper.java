package myy803.traineeship_app.mappers;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import myy803.traineeship_app.domain_model.Likes;


@Repository
public interface LikesMapper extends JpaRepository<Likes,Integer>{
	
	List<Likes> findByInterest(String interest);
	List<Likes> findByProfessorUsername(String username);
	List<Likes> findByStudentUsername(String username);
	@Modifying
	@Transactional
	@Query(value="UPDATE likes  SET professor_username = :username WHERE interest_id = :id",nativeQuery=true)
	void updateLikesProfessorUsername(@Param("username") String professorUsername, @Param("id") Integer id);
	
	
}