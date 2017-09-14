package be.formation.domain;

import org.jibble.pircbot.PircBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import be.formation.beans.ChatUser;
import be.formation.beans.Function;
import be.formation.services.ChatUserServices;
import be.formation.services.FunctionServices;

@PropertySource("classpath:application.properties")
public class ChatBot extends PircBot {

	@Value("${chatbot.channelName}")
	private String channel;
	@Autowired
	private ChatUserServices services;
	@Autowired
	private FunctionServices fctServices;
	@Autowired
	private CommandManager cmdManager;

	public ChatBot() {
		super();
		this.setName("FeulBot");
	}

	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		ChatUser usr = updateMessageUser(sender);
		String cmd = message.split(" ")[0];
		if(cmd.startsWith("!")) {
			if(fctServices.findAllActive(true).contains(new Function(cmd))) {
				commandSwitcher(usr, message);
			}
		}
	}
	
	private ChatUser updateMessageUser(String sender) {
		ChatUser usr = services.findOneUser(sender);
		if (usr == null) {
			services.createUser(new ChatUser(sender));
		} else {
			services.incrMessagesSent(usr);
		}
		return usr;
		
	}
	
	private void commandSwitcher(ChatUser usr, String message) {
		
		String cmd = message.split(" ")[0];
		switch( cmd){
		case "!event":
			cmdManager.cmdEvent(usr, message);
			break;
		case "!participate":
			cmdManager.cmdParticipate(usr, message);
			break;
			
		default:
			break;
		}
	}
	
	
}
