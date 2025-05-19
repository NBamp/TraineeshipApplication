package myy803.traineeship_app.services;

import myy803.traineeship_app.domain_model.User;
import myy803.traineeship_app.dtos.UserDto;


public interface UserService{
	
	void saveUser(UserDto user);
	boolean isUserPresent(UserDto user);
	//User findById(String username);
	
}