package be.formation.services;

import java.util.List;

import be.formation.beans.ChatUser;
import be.formation.beans.Message;

public interface MessageServices{
	
	public void createMessage(String str, ChatUser usr);
	
	public List<Message> showHistory();

}
