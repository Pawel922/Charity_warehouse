package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@Controller
public class DonationController {

    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;

    public DonationController (CategoryRepository categoryRepository,
                               InstitutionRepository institutionRepository) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }

    @RequestMapping("/donation")
    public String displayForm() {
        return "form";
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }
}
