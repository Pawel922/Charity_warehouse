package pl.coderslab.charity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {
	
	@RequestMapping("/admin")
	public String displayAdminPage() {
		return "admin-index";
	}

}
