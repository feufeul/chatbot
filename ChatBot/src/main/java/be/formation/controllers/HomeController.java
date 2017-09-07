package be.formation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.formation.services.ChatUserServices;

@Controller
public class HomeController {

	@Autowired
	private ChatUserServices service;

	@RequestMapping("/users")
	public String usersPage(@RequestParam(value = "message", required = false, defaultValue = "Welcome") String str,
			Model model) {
		model.addAttribute("users", service.findAll());
		return "users";
	}
}