package be.formation.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${chatbot.channelName}")
	private String channel;
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

		bot.op(channel, name);
		ChatUser usr = repoUser.findOne(name);
		usr.setModerator(true);
		repoUser.save(usr);

	}

	@Override
	public void downModerator(String name) {

		bot.deOp(channel, name);
		ChatUser usr = repoUser.findOne(name);
		usr.setModerator(false);
		repoUser.save(usr);

	}

	@Override
	public void createEvent(String sender, String message) {

		bot.sendMessage(channel, "Nous allons créer ton événement");
		try {
			Event event = new Event(Utils.stringToDescription(message), Utils.stringToDate(message));
			if (event.getDate() != null) {
				repoEvent.save(event);
				bot.sendMessage(channel, "Ton événement a bien été créé");
			}
		} catch (Exception e) {
			bot.sendMessage(channel, "@" + sender + " " + e.getMessage());
			bot.sendMessage(channel, "Pour inscrire un nouvel événement, il te faut l'inscrire selon ce modèle : "
					+ "!event (description) yyyy MM dd hh mm ou celui-ci : " + "!event (description) yyyy MM dd");
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
		int id = Utils.stringToParticipation(message);
		Event event = repoEvent.findOne(id);
		if (event != null) {
			List<ChatUser> users = event.getUsers();
			ChatUser user = repoUser.findOne(sender);
			if (users.contains(user)) {
				bot.sendMessage(channel, "Tu participes déjà à cet événement");
			} else {
				users.add(user);
				event.setUsers(users);
				repoEvent.save(event);
				bot.sendMessage(channel, "Tu es désormais inscrit à cet événement !");
			}
		} else {
			bot.sendMessage(channel, "Cet événement n'existe pas..");
		}
	}

	@Override
	public void deleteUser(ChatUser usr) {
		repoUser.delete(usr);
	}

	@Override
	public void deleteEvent(Event event) {
		repoEvent.delete(event);
	}

	@Override
	public void updateEvent(int id, LocalDateTime date, String description) {
		Event event = new Event(id, date, description);
		repoEvent.save(event);
	}

	@Override
	public List<ChatUser> getParticipants(int id) {
		return repoEvent.findOne(id).getUsers();
	}

}
