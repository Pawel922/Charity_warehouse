package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.CurrentUser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Controller
public class DonationController {

    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public DonationController (CategoryRepository categoryRepository,
                               InstitutionRepository institutionRepository,
                               DonationRepository donationRepository) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @RequestMapping("/donation")
    public String displayForm(@AuthenticationPrincipal CurrentUser customUser,
    		Model model) {
    	model.addAttribute("loggedUser", customUser.getUser());
        return "form";
    }

    @PostMapping("/donation")
    public String processForm(@RequestParam long[] categoryId,
                              @RequestParam int quantity,
                              @RequestParam long institutionId,
                              @RequestParam String street,
                              @RequestParam String city,
                              @RequestParam String zipCode,
                              @RequestParam String phoneNumber,
                              @RequestParam String pickUpDate,
                              @RequestParam String pickUpTime,
                              @RequestParam String pickUpComment,
                              @AuthenticationPrincipal CurrentUser customUser) {
        List<Category> categoryList = new ArrayList<>();
        for (long id : categoryId) {
            categoryRepository.findById(id).ifPresent(categoryList::add);
        }
        Donation donation = new Donation();
        donation.setCategories(categoryList);
        donation.setQuantity(quantity);
        institutionRepository.findById(institutionId).ifPresent(donation::setInstitution);
        donation.setUser(customUser.getUser());
        donation.setStreet(street);
        donation.setCity(city);
        donation.setZipCode(zipCode);
        donation.setPhoneNumber(phoneNumber);
        donation.setPickUpDate(dateConverter(pickUpDate));
        donation.setPickUpTime(timeConverter(pickUpTime));
        donation.setPickUpComment(pickUpComment);
        donationRepository.save(donation);
        return "form-confirmation";
    }
    
    @RequestMapping("/donation/all")
    public String displayAllDonations(Model model) {
    	model.addAttribute("donations", donationRepository.findAll());
    	return "admin-donations";
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    private LocalDate dateConverter(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private LocalTime timeConverter(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
    }
}
