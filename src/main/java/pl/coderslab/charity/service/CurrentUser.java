package pl.coderslab.charity.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {
	
	private final pl.coderslab.charity.entity.User user;
	
	public CurrentUser(String email, 
			String password,
			Collection<? extends GrantedAuthority> authorities,
			pl.coderslab.charity.entity.User user) {
		super(email, password, authorities);
		this.user = user;
	}
	
	public pl.coderslab.charity.entity.User getUser() {
		return user;
	}
}
