package myy803.traineeship_app.config;

import myy803.traineeship_app.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import myy803.traineeship_app.strategies.professor.AssignmentBasedOnInterests;
import myy803.traineeship_app.strategies.professor.AssignmentBasedOnLoad;
import myy803.traineeship_app.strategies.professor.SupervisorAssignmentFactory;
import myy803.traineeship_app.strategies.student.PositionSearchFactory;
import myy803.traineeship_app.strategies.student.SearchBasedOnInterests;
import myy803.traineeship_app.strategies.student.SearchBasedOnLocation;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class Applicationconfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
		}
	
	@Bean 
	public PositionSearchFactory positionSearchFactory() {
		return new PositionSearchFactory();
	}
	
	@Bean 
	public SupervisorAssignmentFactory supervisorAssignmentFactory() {
		return new SupervisorAssignmentFactory();
	}
	
	@Bean
	public SearchBasedOnInterests searchBasedOnInterests() {
		return new  SearchBasedOnInterests();
	}
	
	@Bean
	public SearchBasedOnLocation searchBasedOnLocation() {
		return new  SearchBasedOnLocation();
	}
	
	@Bean 
	public AssignmentBasedOnInterests assignmentBasedOnInterests() {
		return new AssignmentBasedOnInterests(); 
	}
	
	@Bean 
	public AssignmentBasedOnLoad assignmentBasedOnLoad() {
		return new AssignmentBasedOnLoad(); 
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {return new BCryptPasswordEncoder();}

	@Autowired
	private CustomSecuritySuccessHandler customSecuritySuccessHandler;

	@Bean
	public UserDetailsService userDetailsService() {
		return new IUserService() {
		};
	}



	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	/*
	 * DaoAuthenticationProvider is an AuthenticationProvider implementation that uses
	 * a UserDetailsService
	 * and PasswordEncoder
	 * to authenticate a username and password.
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(bCryptPasswordEncoder());

		return authProvider;
	}

	/*
	 * Authorization configuration ....
	 */

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
				(authz) -> authz
						.requestMatchers("/", "/login", "/register", "/save").permitAll()
						.requestMatchers("/student/**").hasAnyAuthority("STUDENT")
						.requestMatchers("/company/**").hasAnyAuthority("COMPANY_MEMBER")
						.requestMatchers("/professor/**").hasAnyAuthority("PROFESSOR")
						.requestMatchers("/committee/**").hasAnyAuthority("COMMITTEE_MEMBER")
						.anyRequest().authenticated()
		);

		http.formLogin(fL -> fL.loginPage("/login")
				.successHandler(new CustomSecuritySuccessHandler())
				.failureUrl("/login?error=true")
				.usernameParameter("username")
				.passwordParameter("password"));

		http.logout(logOut -> logOut.logoutUrl("/logout")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
		);

		http.authenticationProvider(authenticationProvider());

		return http.build();
	}
}


	





