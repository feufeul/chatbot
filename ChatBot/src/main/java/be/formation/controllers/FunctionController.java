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

import be.formation.beans.Function;
import be.formation.services.FunctionServices;


@Controller
public class FunctionController {
	
	@Autowired
	private FunctionServices fctService;
	
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
	
	@PostMapping("/deleteFunction/{id}")
	public String deleteFunction(@PathVariable String id) {
		fctService.deleteFunction(fctService.findOne(id));
		return "redirect:/functions";
	}
	
	@PostMapping("/createFunction")
	public String createFunction(@RequestParam(value = "function") String function,
			@RequestParam(value = "isActive", required=false) boolean isActive,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "signature") String signature) {
		if(fctService.findOne(function)==null) {
			fctService.editFunction(function, isActive, description, signature);
		}
		return "redirect:/functions";
	}

	@RequestMapping("/functions")
	public String functionsPage(Model model, Pageable pageable) {
		Page<Function> fctPage = fctService.displayAllFunction(pageable);
		PageWrapper<Function> page = new PageWrapper<>(fctPage, "/functions");
		model.addAttribute("functions", page.getContent());
		model.addAttribute("page", page);
		return "functions";
	}

}
