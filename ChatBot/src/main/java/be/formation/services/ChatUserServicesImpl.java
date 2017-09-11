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
public class ChatUserServicesImpl implements ChatUserServices {

	@Autowired
	private ChatBot bot;
	@Autowired
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
	public ChatUser findOneUser(String str) {

		return repoUser.findOne(str);

	}

	@Override
	public List<ChatUser> findAllUser() {

		return repoUser.findAll();
	}

	@Override
	public void upModerator(String name) {

		bot.op("#feufeul_talmie", name);
		ChatUser usr = repoUser.findOne(name);
		usr.setModerator(true);
		repoUser.save(usr);

	}

	@Override
	public void downModerator(String name) {

		bot.deOp("#feufeul_talmie", name);
		ChatUser usr = repoUser.findOne(name);
		usr.setModerator(false);
		repoUser.save(usr);

	}

	@Override
	public void createEvent(String sender, String message) {

		bot.sendMessage("#feufeul_talmie", "Nous allons créer ton événement");
		try {
			Event event = new Event(Utils.stringToDescription(message), Utils.stringToDate(message));
			if (event.getDate() != null)
				repoEvent.save(event);
		} catch (Exception e) {
			bot.sendMessage("#feufeul_talmie", "@"+sender+ " " + e.getMessage());
			bot.sendMessage("#feufeul_talmie", "Pour inscrire un nouvel événement, il te faut l'inscrire selon ce modèle : "
					+ "!event (description) yyyy MM dd hh mm ou celui-ci : "
					+ "!event (description) yyyy MM dd");
		}

	}

	@Override
	public Event findOneEvent(int id) {

		return repoEvent.findOne(id);

	}

	@Override
	public List<Event> findAllEvents() {
		return repoEvent.findAll();
	}

	@Override
	public void participateEvent(String sender, String message) {
		bot.sendMessage("#feufeul_talmie", "Nous prenons en compte votre participation");
		int id = Utils.stringToParticipation(message);
		Event event = repoEvent.findOne(id);
		List<ChatUser> users = event.getUsers();
		ChatUser user = repoUser.findOne(sender);
		users.add(user);
		event.setUsers(users);
		repoEvent.save(event);
	}

	@Override
	public void deleteUser(ChatUser usr) {

		repoUser.delete(usr);
	}

	@Override
	public void deleteEvent(Event event) {

		repoEvent.delete(event);

	}

}
