package be.formation.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import be.formation.beans.ChatUser;
import be.formation.beans.Event;
import be.formation.domain.ChatBot;
import be.formation.repository.ChatUserRepository;
import be.formation.repository.EventRepository;

@Service
@PropertySource("classpath:application.properties")
public class ChatUserServicesImpl implements ChatUserServices{

	@Autowired
	private ChatBot bot;
	private ChatUserRepository repoUser;
	@Autowired
	private EventRepository repoEvent;
	
	@Autowired
	public ChatUserServicesImpl(ChatUserRepository repo) {
		super();
		this.repoUser = repo;
	}
	
	@Override
	public void incrMessagesSent(ChatUser usr) {

		usr.incrmessagesSent();
		repoUser.save(usr);
	}

	@Override
	public void createUser(ChatUser usr) {

		repoUser.save(usr);
		
	}

	@Override
	public ChatUser findOne(String str) {
		
		return repoUser.findOne(str);
		
	}

	@Override
	public List<ChatUser> findAll() {
	
		return repoUser.findAll();
	}

	@Override
	public void unModerator(String name) {
		// TODO Auto-generated method stub
		bot.deOp("#feufeul_talmie", name);
		
	}

	@Override
	public void createEvent(String sender, String message) {
		
		bot.sendMessage("#feufeul_talmie", "Nous allons créer ton événement");
		String[] words = message.split(" ");
		for(String s : words) {
			System.out.println(s);
		}
		Event event = new Event();
		repoEvent.save(event);
		
	}

}
