package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
                              @RequestParam String pickUpComment) {
        List<Category> categoryList = new ArrayList<>();
        for (long id : categoryId) {
            categoryRepository.findById(id).ifPresent(categoryList::add);
        }
        Donation donation = new Donation();
        donation.setCategories(categoryList);
        donation.setQuantity(quantity);
        institutionRepository.findById(institutionId).ifPresent(donation::setInstitution);
        donation.setStreet(street);
        donation.setCity(city);
        donation.setZipCode(zipCode);
        donation.setPhoneNumber(phoneNumber);
        donation.setPickUpDate(dateConverter(pickUpDate));
        donation.setPickUpTime(timeConverter(pickUpTime));
        donation.setPickUpComment(pickUpComment);
        return "form-confirmation";

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
