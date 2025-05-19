package myy803.traineeship_app.mappers;


import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import myy803.traineeship_app.domain_model.User;
import myy803.traineeship_app.domain_model.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface UserMapper extends JpaRepository<User,String>{

	@Modifying
	@Query(value = "ALTER TABLE your_table_name MODIFY COLUMN role VARCHAR(50);", nativeQuery = true)
	void modifyRoleColumn();

	List<User> findByRole(Role role);
	
	
	
}
