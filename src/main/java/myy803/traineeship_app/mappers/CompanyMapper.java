package myy803.traineeship_app.mappers;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import myy803.traineeship_app.domain_model.Company;


public interface CompanyMapper extends JpaRepository<Company, String>{
	
	Optional<Company> findByUsername(String username);
	List<Company>  findByLocation(String location);
	
}
