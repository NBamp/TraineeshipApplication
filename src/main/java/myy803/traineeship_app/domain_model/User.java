package myy803.traineeship_app.domain_model;



import jakarta.persistence.*;

import java.util.Collection;
import java.util.Collections;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Data
@Entity
@Table(name="users")
public class User implements UserDetails{

	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password",nullable = false)
	private String password;


    @Column(name="role")
    private Role role;
    
	public User() {
		super();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
	     return Collections.singletonList(authority);
	}
	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return password;
	}

    @Override
	public String getUsername() {
		return username;
	}

}
