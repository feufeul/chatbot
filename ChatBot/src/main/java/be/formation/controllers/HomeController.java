package be.formation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.formation.services.ChatUserServices;

@Controller
public class HomeController {

	@Autowired
	private ChatUserServices service;
	
	@RequestMapping("/")
	public String greeting(@RequestParam(value = "message", required = false, defaultValue = "Welcome") String greeting,
			Model model) {
		model.addAttribute("users", service.findAll());
		return "home";
	}
}