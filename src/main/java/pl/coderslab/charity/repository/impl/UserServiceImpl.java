package pl.coderslab.charity.repository.impl;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.repository.UserService;

public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository,
			RoleRepository roleRepository,
			BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	public User findByUserName(String username) {
		return userRepository.findByName(username);
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));    
		user.setEnabled(1);    
		Role userRole = roleRepository.findByName("ROLE_USER");    
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));    
		userRepository.save(user);
	}

}
