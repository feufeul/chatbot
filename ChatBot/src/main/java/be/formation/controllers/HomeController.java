package be.formation.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {



	// Login form
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			response = null;
		}
		model.addAttribute("logout", true);
		return "redirect:/login?logout";
	}

	@RequestMapping("/")
	public String indexPage(@RequestParam(value = "message", required = false, defaultValue = "Welcome") String str,
			Model model) {
		return "home";
	}

	@RequestMapping("/home")
	public String homePage(Model model) {
		return "home";
	}


	@GetMapping("/403")
	public String error403() {
		return "403";
	}

}