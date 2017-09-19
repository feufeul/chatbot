package be.formation.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.formation.beans.Event;
import be.formation.services.ChatUserServices;

@Controller
public class EventController {
	
	@Autowired
	private ChatUserServices service;
	


	@RequestMapping("/events")
	public String eventsPage(Model model, Pageable pageable) {
		Page<Event> eventPage = service.displayAllEvent(pageable);
		PageWrapper<Event> page = new PageWrapper<>(eventPage, "/events");
		model.addAttribute("page", page);
		model.addAttribute("events", page.getContent());
		return "events";
	}
	
	@PostMapping("/deleteEvent/{id}")
	public String deleteEvent(@PathVariable int id) {
		service.deleteEvent(service.findOneEvent(id));
		return "redirect:/events";
	}

	@PostMapping("/modifyEvent/")
	public String modifyEvent(@RequestParam(value = "id") int id,
			@RequestParam(value = "date") String dateStr,
			@RequestParam(value = "description") String description) {
		if(service.findOneEvent(id)!= null) {
			String[] strList = dateStr.split(" ");
			List<Integer> intList = new ArrayList<>();
			for (String s : strList) {
				intList.add(Integer.parseInt(s));
			}
			service.updateEvent(id,
					LocalDateTime.of(intList.get(0), intList.get(1), intList.get(2), intList.get(3), intList.get(4)),
					description);
		}
		return "redirect:/events";
	}
	
	@PostMapping("/displayEvent/{id}")
	public String displayEvent(@PathVariable int id, Model model) {
		model.addAttribute("event", service.findOneEvent(id));
		model.addAttribute("participants", service.getParticipants(id));
		return "displayevent";
	}

}
