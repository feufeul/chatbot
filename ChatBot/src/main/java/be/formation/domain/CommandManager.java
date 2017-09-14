package be.formation.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import be.formation.beans.ChatUser;
import be.formation.services.ChatUserServices;

@PropertySource("classpath:application.properties")
public class CommandManager {

	@Value("${chatbot.channelName}")
	private String channel;
	@Autowired
	private ChatUserServices services;
	@Autowired
	private ChatBot bot;

	public CommandManager() {
		
	}
	
	public void cmdEvent(ChatUser usr, String message) {
		if(usr.getIsModerator()) {
			services.createEvent(usr.getName(), message);
			bot.sendMessage(channel, "Ton événement a bien été créé");
		}else
			bot.sendMessage(channel, "Cette commande n'est pas pour toi !");
	}
	
	public void cmdParticipate(ChatUser usr, String message) {
		services.participateEvent(usr.getName(), message);
	}
		
	
	
	

}
