package be.formation.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.formation.beans.Message;
import be.formation.services.ChatUserServices;
import be.formation.services.FunctionServices;
import be.formation.services.MessageServices;

@Controller
public class HomeController {

	@Autowired
	private ChatUserServices service;
	@Autowired
	private FunctionServices fctService;
	@Autowired
	private MessageServices msgServices;

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
	

	@RequestMapping("/users")
	public String usersPage(Model model) {
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
	
	@PostMapping("/displayEvent/{id}")
	public String displayEvent(@PathVariable int id, Model model) {
		model.addAttribute("participants", service.getParticipants(id));
		return "displayevent";
	}
	
	@PostMapping("/switchFunction/{id}")
	public String switchEvent(@PathVariable(value="id") String id) {
		fctService.switchEnable(id);
		return "redirect:/functions";
	}
	
	@PostMapping("/modifyFunction")
	public String modifyFunction(@RequestParam(value = "function") String function,
			@RequestParam(value = "isActive", required=false) boolean isActive,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "signature") String signature) {
		if(fctService.findOne(function)!=null) {
			fctService.editFunction(function, isActive, description, signature);
		}
		return "redirect:/functions";
	}
	
	@RequestMapping("/chathistory")
	public String chathistory(Model model) {
		// Paginate the different messages
		Page<Message> messages = msgServices.displayAll(new PageRequest(1, 20));
		model.addAttribute("chathistory", messages);
		return "chathistory";
	}

	@RequestMapping("/functions")
	public String functionsPage(Model model) {
		model.addAttribute("functions", fctService.findAll());
		return "functions";
	}

	@GetMapping("/403")
	public String error403() {
		return "403";
	}

}