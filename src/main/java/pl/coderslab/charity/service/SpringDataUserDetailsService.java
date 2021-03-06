package pl.coderslab.charity.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserService;

public class SpringDataUserDetailsService implements UserDetailsService {
	
	private UserService userService;
	
	@Autowired
	public void setUserRepository(UserService userService) {
		this.userService = userService;    
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.findByUserName(email);
		if (user == null) {
			throw new UsernameNotFoundException(email); 
		}    
		Set <GrantedAuthority> grantedAuthorities = new HashSet<>();    
		user.getRoles().forEach(r -> grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
		return new CurrentUser(user.getEmail(),user.getPassword(), grantedAuthorities, user);
	}
}
