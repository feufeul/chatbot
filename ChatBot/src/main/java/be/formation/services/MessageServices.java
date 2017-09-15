package be.formation.services;

import be.formation.beans.ChatUser;

public interface MessageServices{
	
	public void createMessage(String str, ChatUser usr);

}
