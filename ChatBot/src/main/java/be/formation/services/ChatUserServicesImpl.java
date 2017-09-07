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
import be.formation.utils.Utils;

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
	public List<ChatUser> findAllUser() {
	
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
		Event event = new Event("description", Utils.stringToDate(message));
		if(event.getDate() != null)
			repoEvent.save(event);
		
	}

	@Override
	public List<Event> findAllEvents() {
		return repoEvent.findAll();
	}

	@Override
	public void participateEvent(String sender, String message) {
		bot.sendMessage("#feufeul_talmie", "Nous prenons en compte votre participation");
		Event event = repoEvent.findOne(Utils.stringToParticipation(message));
		List<ChatUser> users = event.getUsers();
		ChatUser user = repoUser.findOne(sender);
		users.add(user);
		event.setUsers(users);
		repoEvent.save(event);
	}

}
