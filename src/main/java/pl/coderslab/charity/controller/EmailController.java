package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.EmailSender;

@Controller
public class EmailController {
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;
    private final UserRepository userRepository;
    
    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine,
                           UserRepository userRepository){
    	
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        this.userRepository = userRepository;
    }
    
    @RequestMapping("/send")
    public String sendMessage(@RequestParam String name,
    		@RequestParam String surname,
    		@RequestParam String message) {
        Context context = new Context();
        context.setVariable("header", "Nowa wiadomość");
        context.setVariable("title", "Uzytkownik " + name + " " + surname + " pisze: ");
        context.setVariable("description", message);
        String body = templateEngine.process("template1", context);
        emailSender.sendEmail("", "Formularz kontaktowy - wiadomość", body);
        return "send-confirmation";
    }
    
    @RequestMapping("/link")
    public String sendConfirmationLink(@RequestParam(required=true) String confirmId, @RequestParam(required=true) String email,
    		Model model) {
    	Context context = new Context();
        context.setVariable("header", "Link aktywacyjny");
        context.setVariable("title", "Aby aktywowac konto kliknij poniższy link:");
        context.setVariable("link", "http://localhost:8080/confirm?id=" + confirmId);
        String body = templateEngine.process("template2", context);
        emailSender.sendEmail(email, "Aktywacja konta", body);
    	String message = "Na Twój adres e-mail wysłaliśmy link do aktywacji konta.";
        model.addAttribute("message", message);
        return "register-confirmation";
    	
    }
    
    @PostMapping("/password/link")
    public String sendLinkToResetPassword(@RequestParam String email, Model model) {
    	//create new confirmationId for user - link to change password can be use only once; 
    	User userToChangePassword = userRepository.findByEmail(email).get();
    	String confirmId = createConfirmationID();
    	userToChangePassword.setConfirmationId(confirmId);
    	userRepository.save(userToChangePassword);
    	
    	Context context = new Context();
        context.setVariable("header", "Link do zmiany hasła");
        context.setVariable("title", "Aby zmienić hasło kliknij poniższy link:");
        context.setVariable("link", "http://localhost:8080/password/reset?email=" + email + "&confirmId=" + confirmId);
        String body = templateEngine.process("template2", context);
        emailSender.sendEmail(email, "Zmiana hasła", body);
    	String message = "Na adres " + email + " wysłaliśmy link do zmiany hasła.";
        model.addAttribute("message", message);
        return "register-confirmation";
    }
    
    @RequestMapping("/password/reset")
    public String openFormToSetNewPassword(@RequestParam String email, 
    		@RequestParam(required=true) String confirmId, 
    		Model model) {
    	User userToSetNewPassword = userRepository.findByEmail(email).get();
    	if(confirmId.equals(userToSetNewPassword.getConfirmationId())) {
    		userToSetNewPassword.setConfirmationId(null);
    		userRepository.save(userToSetNewPassword);
    		model.addAttribute("email",email);
    		return "reset-password";
    	}
    	String message = "Próba zmiany hasła nie powiodła się.";
    	model.addAttribute("message", message);
    	return "register-confirmation";
    }
    
    @PostMapping("password/reset/{email}")
    public String saveNewPassword(@PathVariable String email, 
    		@RequestParam String password, 
    		Model model) {
    	User userToSetNewPassword = userRepository.findByEmail(email).get();
    	userToSetNewPassword .setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
    	userRepository.save(userToSetNewPassword);
    	String message = "Twoje hasło zostało zmienione.";
    	model.addAttribute("message",message);
    	return "register-confirmation";
    }
    
    private String createConfirmationID() {
        return java.util.UUID.randomUUID().toString();
    }
}