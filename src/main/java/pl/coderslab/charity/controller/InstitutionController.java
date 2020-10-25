package pl.coderslab.charity.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.charity.entity.Institution;
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
	
	@RequestMapping("/institution/edit/{id}")
	public String displayFormToEdit(Model model, @PathVariable long id) {
		Optional<Institution> instToEdit = institutionRepository.findById(id);
		if(instToEdit.isPresent()) {
			model.addAttribute("institution",instToEdit.get());
		}
		return "institution-edit";
	}
	
	@PostMapping("/institution/edit/{id}")
	public String processFormToEdit(@PathVariable long id, 
			@ModelAttribute Institution institution ) {
		Optional<Institution> optInstToUpdate = institutionRepository.findById(id);
		if(optInstToUpdate.isPresent()) {
			Institution instToUpdate = optInstToUpdate.get();
			instToUpdate.setName(institution.getName());
			instToUpdate.setDescription(institution.getDescription());
			institutionRepository.save(instToUpdate);
		}
		return "redirect:/institution/all";
	}
	

	@ModelAttribute("loggedUser")
	public User getLoggedUser(@AuthenticationPrincipal CurrentUser customUser) {
		return customUser.getUser();
	}
}
