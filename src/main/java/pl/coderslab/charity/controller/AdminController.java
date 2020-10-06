package pl.coderslab.charity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.CurrentUser;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {
	
	@RequestMapping("/admin")
	public String displayAdminPage() {
		return "admin-index";
	}
	
	@ModelAttribute("loggedUser")
	public User getLoggedUser(@AuthenticationPrincipal CurrentUser customUser) {
		return customUser.getUser();
	}

}
