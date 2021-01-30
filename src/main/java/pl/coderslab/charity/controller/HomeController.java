package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.repository.UserService;
import pl.coderslab.charity.service.CurrentUser;
import pl.coderslab.charity.service.EmailSender;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final EmailSender emailSender;

    @Autowired
    public HomeController(InstitutionRepository institutionRepository,
                          DonationRepository donationRepository,
                          UserService userService,
                          UserRepository userRepository,
                          EmailSender emailSender) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.emailSender = emailSender;
    }

    @RequestMapping("/")
    public String homeAction(@AuthenticationPrincipal CurrentUser customUser, Model model){
    	if(customUser != null) {
    		model.addAttribute("loggedUser", customUser.getUser());
    	}
        return "index";
    }
    
    @RequestMapping("/register")
    public String displayRegisterForm(Model model) {
    	model.addAttribute("userToRegister", new User());
    	return "register";
    }
    
    @PostMapping("/register")
    public String processRegisterForm(@ModelAttribute("userToRegister") @Valid User userToRegister,
    		BindingResult result,
    		Model model) {
    	if(result.hasErrors()) {
    		return "register";
    	} else {
    		userToRegister.setConfirmationId(createConfirmationID());
    		userService.saveUser(userToRegister);
    		emailSender.sendEmail(userToRegister.getEmail(), 
    				"Link aktywacyjny", 
    				"Aby aktywować konto, kliknij link " 
    				+ "http://localhost:8080/confirm?id=" + userToRegister.getConfirmationId());
    		String message = "Na Twój adres e-mail wysłaliśmy link do aktywacji konta.";
    		model.addAttribute("message", message);
    		return "register-confirmation";
    	}
    }
    
    @RequestMapping("/confirm")
    public String completeRegistration(@RequestParam(name="id", required=true) String confirmationId, Model model) {
    	Optional<User> optUser = userRepository.findByConfirmationId(confirmationId);
    	if(optUser.isPresent()) {
    		User user = optUser.get();
    		if(!optUser.get().getConfirmationStatus()) {
    			user.setConfirmationStatus(true);
    			user.setConfirmationId(null);
    			userRepository.save(user);
    		}
    		String message = "Witaj " + user.getName() + " " + user.getSurname() + ", twoje konto jest aktywne.";
			model.addAttribute("message", message);
    	} else {
    		String message = "Coś poszło nie tak. Spróbuj jeszcze raz.";
    		model.addAttribute("message", message);
    	}
    	return "register-confirmation";
    }
    
    @RequestMapping("/send")
    public String sendMessage(@RequestParam String name,
    		@RequestParam String surname,
    		@RequestParam String message) {
    	String title= "Wiadomość od " + name + " " + surname;
    	emailSender.sendEmail("", title, message);
    	return "send-confirmation";
    }
    
    @RequestMapping("/login")
    public String displayLoginForm() {
    	return "login";
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    @ModelAttribute("numOfPackages")
    public int getNumberOfPackages() {
        return donationRepository.findAll().stream().mapToInt(Donation::getQuantity).sum();
    }

    @ModelAttribute("numOfDonations")
    public long getNumberOfDonations() {
        return donationRepository.findAll().stream().count();
    }
    
    private String createConfirmationID() {
        return java.util.UUID.randomUUID().toString();
    }
}
