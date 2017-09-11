package be.formation.domain;

import org.jibble.pircbot.PircBot;
import org.springframework.beans.factory.annotation.Autowired;
import be.formation.beans.ChatUser;
import be.formation.services.ChatUserServices;

public class ChatBot extends PircBot {

	@Autowired
	private ChatUserServices services;

	public ChatBot() {
		super();
		this.setName("FeulBot");
	}

	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		// TODO export this to an external method, this will add new User or increment
		ChatUser usr = services.findOneUser(sender);
		if (usr == null) {
			System.out.println("create in db");
			services.createUser(new ChatUser(sender));
		} else {
			System.out.println("update db");
			services.incrMessagesSent(usr);
		}
		if (usr.getName().equals("feufeul_talmie")) {
			if (message.startsWith("!event ")) {
				services.createEvent(sender, message);
			}
		}

		if (message.startsWith("!participate")) {
			services.participateEvent(sender, message);
		}

	}
}
