package be.formation.services;


import java.util.List;

import be.formation.beans.ChatUser;

public interface ChatUserServices {

	/**
	 * Increment de number of messages that have been sent
	 * @param usr
	 */
	public void incrMessagesSent(ChatUser usr);

	/**
	 * Creating a new User in the database
	 * @param usr
	 */
	public void createUser(ChatUser usr);
	
	/**
	 * Find the user in the database
	 * @param The nickname of the chatter
	 * @return An entity of the ChatUser
	 */
	public ChatUser findOne(String str);

	public List<ChatUser> findAll();

}
