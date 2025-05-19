package myy803.traineeship_app.services;

import java.util.Optional;

import myy803.traineeship_app.domain_model.Role;
import myy803.traineeship_app.dtos.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import myy803.traineeship_app.domain_model.User;
import myy803.traineeship_app.mappers.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class IUserService implements UserService, UserDetailsService {

	public IUserService(){

	}
	
	@Autowired
	private UserMapper user_mapper;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public IUserService(ModelMapper modelMapper) {this.modelMapper = modelMapper;}
	
	PropertyMap<UserDto, User>  skipModifiedFieldsMap = new PropertyMap<UserDto, User>() {
		@Override
		protected void configure() {
			skip().setRole(null);
		}
	};
	
	@Override
	public void saveUser(UserDto user) {
		User userToSave = modelMapper.map(user, User.class);
		String encodedPassword = bCryptPasswordEncoder.encode(userToSave.getPassword());
		userToSave.setPassword(encodedPassword);
		userToSave.setRole(fromValue(user.getRole()));
		user_mapper.save(userToSave);

	}

	private static Role fromValue(String value){
		for (Role role : Role.values()) {
			if (role.getValue().equalsIgnoreCase(value)) {
				return role;
			}
		}
		return null;
	}
	
	@Override
	public boolean isUserPresent(UserDto user) {
		Optional<User> PresentUser = user_mapper.findById(user.getUsername());
		return PresentUser.isPresent();
	}
	
	/*@Override
	public User findById(String username) {
		return user_mapper.findById(username).orElseThrow(
				()-> new UsernameNotFoundException(String.format("USER_NOT_FOUND",username)
				));
	}*/

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return 	user_mapper.findById(username).orElseThrow(()->new UsernameNotFoundException(String.format("USER_NOT_FOUND",username)));
	}
	
	
	
	
	
	
}