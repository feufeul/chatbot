package be.formation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import be.formation.beans.Message;
import be.formation.services.MessageServices;

@Controller
public class MessageController {


	@Autowired
	private MessageServices msgServices;
	
    @RequestMapping(value = "/chathistory")
    public String list(Model model, Pageable pageable){
        Page<Message> msgPage = msgServices.displayAll(pageable);
        PageWrapper<Message> page = new PageWrapper<Message>(msgPage, "/chathistory");
        model.addAttribute("chathistory", page.getContent());
        model.addAttribute("page", page);
        return "chathistory";
    }

}
