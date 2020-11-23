package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.CurrentUser;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {
	
	final UserRepository userRepository;
	
	@Autowired
	public AdminController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@RequestMapping("/admin")
	public String displayAdminPage() {
		return "admin-index";
	}
	
	@ModelAttribute("loggedUser")
	public User getLoggedUser(@AuthenticationPrincipal CurrentUser customUser) {
		return customUser.getUser();
	}
	
	@RequestMapping("/admin/all")
	public String displayAllAdmins(Model model) {
		model.addAttribute("admins",userRepository.getAllAdmins());
		return "admin-admins";
	}

}
