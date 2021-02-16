package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import pl.coderslab.charity.service.EmailSender;

@Controller
public class EmailController {
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;
    
    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine){
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }
    
    @RequestMapping("/send")
    public String sendMessage(@RequestParam String name,
    		@RequestParam String surname,
    		@RequestParam String message) {
        Context context = new Context();
        context.setVariable("header", "Nowa wiadomość");
        context.setVariable("title", "Uzytkownik " + name + " " + surname + " pisze: ");
        context.setVariable("description", message);
        String body = templateEngine.process("template", context);
        emailSender.sendEmail("", "Formularz kontaktowy - wiadomość", body);
        return "send-confirmation";
    }
}