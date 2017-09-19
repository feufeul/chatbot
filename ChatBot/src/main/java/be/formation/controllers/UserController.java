package be.formation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.formation.beans.ChatUser;
import be.formation.services.ChatUserServices;
import be.formation.services.MessageServices;

@Controller
public class UserController {

	@Autowired
	private ChatUserServices chatterService;
	@Autowired
	private MessageServices msgServices;
	
	@RequestMapping("/users")
	public String usersPage(Model model, Pageable pageable) {
		Page<ChatUser> userPage = chatterService.displayAllUser(pageable);
		PageWrapper<ChatUser> page = new PageWrapper<>(userPage, "/users");
		model.addAttribute("users", page.getContent());
		model.addAttribute("page", page);
		return "users";
	}
	
	@PostMapping("/upModerator/{id}")
	public String upModerator(@PathVariable String id, 
			@RequestParam(value = "user", required = false) String user) {
		if (user.equals("chatter")) {
			chatterService.downModerator(id);
		} else {
			chatterService.upModerator(id);
		}
		return "redirect:/users";
	}
	

	@PostMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable String id) {
		chatterService.deleteUser(chatterService.findOneUser(id));
		return "redirect:/users";
	}


	
}
