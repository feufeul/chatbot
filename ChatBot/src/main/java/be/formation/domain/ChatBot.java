package be.formation.domain;

import org.jibble.pircbot.PircBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import be.formation.beans.ChatUser;
import be.formation.beans.Function;
import be.formation.services.ChatUserServices;
import be.formation.services.FunctionServices;
import be.formation.services.MessageServices;

@PropertySource("classpath:application.properties")
public class ChatBot extends PircBot {

	@Value("${chatbot.superadmin}")
	private String superadmin;
	@Value("${chatbot.channelName}")
	private String channel;
	@Autowired
	private MessageServices msgServices;
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
		//TODO I have to use external Utils to upgrade the modularity
		ChatUser usr = updateMessageUser(sender);
		msgServices.createMessage(message, usr);
		String cmd = message.split(" ")[0];
		if (cmd.startsWith("!")) {
			if (cmd.equals("!cmd")) {
				// The only admin here that's enabled to custo command
				if (usr.getName().equals(superadmin)) {
					// Take the first argument of the message to be a command
					if (message.split(" ")[1].startsWith("!")) {
						fctServices.createFunction(message.split(" ")[1]);
					}
				}
			}
			if (fctServices.findAllActive(true).contains(new Function(cmd))) {
				commandSwitcher(usr, message);
			}
		}
	}

	/**
	 * Update db with new Chatter or new message
	 * @param sender
	 * @return
	 */
	private ChatUser updateMessageUser(String sender) {
		ChatUser usr = services.findOneUser(sender);
		if (usr == null) {
			services.createUser(new ChatUser(sender));
		} else {
			services.incrMessagesSent(usr);
		}
		return usr;

	}

	/**
	 * The controller which can redirect to the right method
	 * @param usr, the sender of the message
	 * @param message, the message sent
	 */
	private void commandSwitcher(ChatUser usr, String message) {

		String cmd = message.split(" ")[0];
		switch (cmd) {
		case "!event":
			cmdManager.cmdEvent(usr, message);
			break;
		case "!participate":
			cmdManager.cmdParticipate(usr, message);
			break;
		case "!eventlist":
			cmdManager.cmdEventList();
			break;
		case "!cmdlist":
			cmdManager.cmdCmdList();
			break;
		case "!man":
			cmdManager.cmdMan(message);
			break;
		case "!mod":
			cmdManager.cmdMod(usr,message);
			break;
		case "!unmod":
			cmdManager.cmdUnmod(usr,message);
			break;
		default:
			break;
		}
	}

}
