package be.formation.domain;

import org.jibble.pircbot.PircBot;
import org.springframework.beans.factory.annotation.Autowired;
import be.formation.beans.ChatUser;
import be.formation.beans.Function;
import be.formation.services.ChatUserServices;
import be.formation.services.FunctionServices;

public class ChatBot extends PircBot {

	@Autowired
	private ChatUserServices services;
	@Autowired
	private FunctionServices fctServices;

	public ChatBot() {
		super();
		this.setName("FeulBot");
	}

	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		ChatUser usr = updateMessageUser(sender);
		String cmd = message.split(" ")[0].substring(0);
		if(cmd.startsWith("!")) {
			if(fctServices.findAllActive(true).contains(new Function(cmd))) {
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
	}
	
	private ChatUser updateMessageUser(String sender) {
		// TODO export this to an external method, this will add new User or increment
		ChatUser usr = services.findOneUser(sender);
		if (usr == null) {
			System.out.println("create in db");
			services.createUser(new ChatUser(sender));
		} else {
			System.out.println("update db");
			services.incrMessagesSent(usr);
		}
		return usr;
		
	}
}
