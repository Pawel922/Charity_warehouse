package pl.coderslab.charity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
