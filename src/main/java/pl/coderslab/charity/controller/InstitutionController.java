package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.CurrentUser;

@Controller
public class InstitutionController {
	
	private final InstitutionRepository institutionRepository;
	
	@Autowired
	public InstitutionController(InstitutionRepository institutionRepository) {
		this.institutionRepository = institutionRepository;
	}
	
	@RequestMapping("/institution/all")
	public String displayAllInstitutions(Model model) {
		model.addAttribute("institutions", institutionRepository.findAll());
		return "admin-institutions";
	}

	@ModelAttribute("loggedUser")
	public User getLoggedUser(@AuthenticationPrincipal CurrentUser customUser) {
		return customUser.getUser();
	}
}
