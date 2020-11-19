package pl.coderslab.charity.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.CurrentUser;

@Controller
public class UserController {
	
	private final DonationRepository donationRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public UserController(DonationRepository donationRepository, 
			UserRepository userRepository) {
		this.donationRepository = donationRepository;
		this.userRepository = userRepository;
	}
	
	@RequestMapping("/profile")
	public String displayUserProfile() {
		return "user-profile";
	}
	
	@RequestMapping("/user/all")
	public String displayAllUsers(Model model) {
		model.addAttribute("users", userRepository.getAllUsers());
		return "admin-users";
	}
	
	@RequestMapping("/user/edit/{id}/{ignorableError}")
	public String displayFormToEdit(Model model, 
			@PathVariable long id,
			@PathVariable boolean ignorableError) {
		Optional<User> userToEdit = userRepository.findById(id);
		if(userToEdit.isPresent()) {
			model.addAttribute("user", userToEdit.get());
		}
		return "user-edit";
	}
	
	@PostMapping("/user/edit/{id}/{ignorableError}")
	public String processFormToEdit(Model model, @PathVariable long id, 
			@Valid @ModelAttribute User user,
			BindingResult result) {
		User userFromBinding = (User) result.getTarget();
		Optional<User> userToEdit = userRepository.findById(id);
		
		if(userToEdit.isPresent()) {
			User editedUser = userToEdit.get();
			
			//checkList to collect information whether all inputs have correct value
			List<Boolean> checkList = new ArrayList<Boolean>();
			
			//check if user's email was not changed - then ignore error
			if(result.hasErrors()) {
				List<ObjectError> errors = result.getAllErrors();
				
				for(ObjectError error : errors) {
					if(error.getCode().equals("Unique")) {
						String previousEmail = userFromBinding.getEmail();
						if(editedUser.getEmail().equals(previousEmail)) {
							checkList.add(true);
							model.addAttribute("ignorableError", true);
						} else {
							checkList.add(false);
						}
					} else {
						checkList.add(false);
					}
				}
			}
			
			if(!checkList.contains(false)) {
				editedUser.setName(user.getName());
				editedUser.setSurname(user.getSurname());
				editedUser.setEmail(user.getEmail());
				userRepository.save(editedUser);
				return "redirect:/user/all";
			} else {
				return "user-edit";
			}			
		}
		return "user-edit";
	}
	
	@RequestMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable long id) {
		Optional<User> userToDelete = userRepository.findById(id);
		if(userToDelete.isPresent()) {
	
			//find all donations which belong to user and remove them
			List<Donation> donationToDelete = donationRepository.findAllByUserId(id);
			if(!donationToDelete.isEmpty()) {
				for (Donation donation : donationToDelete) {
					donationRepository.delete(donation);
				}
			}
			userRepository.delete(userToDelete.get());
		}
		return "redirect:/user/all";
	}
	

	@RequestMapping("/user-donations")
	public String displayDonationsGivenByUser(@AuthenticationPrincipal CurrentUser customUser, Model model) {
		List<Donation> donations = donationRepository.findAllByUserId(customUser.getUser().getId());
		model.addAttribute("donations", donations);
		return "user-donations";
	}
	
	@ModelAttribute("loggedUser")
	public User getLoggedUser(@AuthenticationPrincipal CurrentUser customUser) {
		return customUser.getUser();
	}

}
