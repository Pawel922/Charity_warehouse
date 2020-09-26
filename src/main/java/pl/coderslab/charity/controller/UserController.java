package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.charity.service.CurrentUser;

@Controller
public class UserController {
	
	@RequestMapping("/profile")
	public String displayUserProfile(@AuthenticationPrincipal CurrentUser customUser, Model model) {
		model.addAttribute("loggedUser", customUser.getUser());
		return "user-profile";
	}

}
