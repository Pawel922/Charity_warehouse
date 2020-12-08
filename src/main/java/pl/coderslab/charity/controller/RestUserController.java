package pl.coderslab.charity.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

@RestController
public class RestUserController {
	
	private UserRepository userRepository;
	
	@Autowired
	public RestUserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/user/enable/{id}")
	public void setUserEnable(@PathVariable long id) {
		Optional<User> optUserToSetEnable = userRepository.findById(id);
		if(optUserToSetEnable.isPresent()) {
			User userToSetEnable = optUserToSetEnable.get();
			userToSetEnable.setEnabled(1);
			userRepository.save(userToSetEnable);
		}
	}
	
	@GetMapping("/user/disable/{id}")
	public void setUserDisable(@PathVariable long id) {
		Optional<User> optUserToSetDisable = userRepository.findById(id);
		if(optUserToSetDisable.isPresent()) {
			User userToSetDisable = optUserToSetDisable.get();
			userToSetDisable.setEnabled(0);
			userRepository.save(userToSetDisable);
		}
	}
}
