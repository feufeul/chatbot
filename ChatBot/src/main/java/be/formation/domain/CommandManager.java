package be.formation.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import be.formation.beans.ChatUser;
import be.formation.beans.Event;
import be.formation.beans.Function;
import be.formation.services.ChatUserServices;
import be.formation.services.FunctionServices;

@PropertySource("classpath:application.properties")
public class CommandManager {

	@Value("${chatbot.channelName}")
	private String channel;
	@Autowired
	private ChatUserServices services;
	@Autowired
	private FunctionServices fctServices;
	@Autowired
	private ChatBot bot;

	public CommandManager() {

	}
	
	public void cmdMan(String message) {
		try {
			String cmdSeeked = message.split(" ")[1];
			Function f = fctServices.findOne(cmdSeeked);
			bot.sendMessage(channel, cmdSeeked + " : " + f.getShortDescription());
		}catch(Exception e) {
			bot.sendMessage(channel, "Mauvaise signature de commande");
		}

	}

	public void cmdEvent(ChatUser usr, String message) {
		if (usr.getIsModerator()) {
			services.createEvent(usr.getName(), message);
		} else
			bot.sendMessage(channel, "Cette commande n'est pas pour toi !");
	}

	public void cmdParticipate(ChatUser usr, String message) {
		services.participateEvent(usr.getName(), message);
	}

	public void cmdEventList() {
		List<Event> eventList = services.findAllEvents();
		for(Event e : eventList) {
			bot.sendMessage(channel, e.toString());
		}
	}

	public void cmdCmd(ChatUser usr, String message) {
		fctServices.createFunction(message);
	}

	public void cmdCmdList() {
		List<Function> fctList = fctServices.findAllActive(true);
		fctList.forEach(f -> bot.sendMessage(channel, f.toString()));
	}

	public void cmdMod(ChatUser usr, String message) {
		try {
			if(usr.getIsModerator()) {
				String chatter = message.split(" ")[1];
				services.upModerator(chatter);
			}
		}catch(Exception e) {
			bot.sendMessage(channel, "Veuillez respecter la signature.");
			bot.sendMessage(channel, "Ecrivez comme suit !mod chatter_name");
		}
	}

	public void cmdUnmod(ChatUser usr, String message) {
		try {
			if(usr.getIsModerator()) {

				String chatter = message.split(" ")[1];
				services.downModerator(chatter);
			}
		}catch(Exception e) {
			bot.sendMessage(channel, "Veuillez respecter la signature.");
			bot.sendMessage(channel, "Ecrivez comme suit !mod chatter_name");
		}
	}
}
