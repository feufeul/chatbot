package be.formation.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.formation.services.ChatUserServices;

@Controller
public class HomeController {

	@Autowired
	private ChatUserServices service;

	@RequestMapping("/")
	public String indexPage(@RequestParam(value = "message", required = false, defaultValue = "Welcome") String str,
			Model model) {
		return "home";
	}

	@RequestMapping("/home")
	public String homePage(@RequestParam(value = "message", required = false, defaultValue = "Welcome") String str,
			Model model) {
		return "home";
	}

	@RequestMapping("/users")
	public String usersPage(@RequestParam(value = "message", required = false, defaultValue = "Welcome") String str,
			Model model) {
		model.addAttribute("users", service.findAllUser());
		model.addAttribute("events", service.findAllEvents());
		return "users";
	}

	@PostMapping("/upModerator/{id}")
	public String upModerator(@PathVariable String id, @RequestParam(value = "user", required = false) String user) {
		if (user.equals("chatter")) {
			service.downModerator(id);
		} else {
			service.upModerator(id);
		}
		return "redirect:/users";
	}

	@PostMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable String id) {
		service.deleteUser(service.findOneUser(id));
		return "redirect:/users";
	}

	@PostMapping("/deleteEvent/{id}")
	public String deleteEvent(@PathVariable int id) {

		service.deleteEvent(service.findOneEvent(id));

		return "redirect:/users";
	}

	@PostMapping("/modifyEvent/")
	public String modifyEvent(@RequestParam(value = "id") int id, @RequestParam(value = "date") String dateStr,
			@RequestParam(value = "description") String description) {

		String[] strList = dateStr.split(" ");
		List<Integer> intList = new ArrayList<>();
		for (String s : strList) {
			intList.add(Integer.parseInt(s));
		}
		service.updateEvent(id,
				LocalDateTime.of(intList.get(0), intList.get(1), intList.get(2), intList.get(3), intList.get(4)),
				description);

		return "redirect:/users";
	}

	@RequestMapping("/functions")
	public String functionsPage(@RequestParam(value = "message", required = false, defaultValue = "Welcome") String str,
			Model model) {

		return "functions";

	}

}