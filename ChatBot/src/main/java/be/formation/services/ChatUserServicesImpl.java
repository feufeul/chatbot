package be.formation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.formation.beans.ChatUser;
import be.formation.repository.ChatUserRepository;

@Service
public class ChatUserServicesImpl implements ChatUserServices{

	private ChatUserRepository repo;
	
	@Autowired
	public ChatUserServicesImpl(ChatUserRepository repo) {
		super();
		this.repo = repo;
	}
	
	@Override
	public void incrMessagesSent(ChatUser usr) {

		usr.incrmessagesSent();
		repo.save(usr);
	}

	@Override
	public void createUser(ChatUser usr) {

		repo.save(usr);
		
	}

	@Override
	public ChatUser findOne(String str) {
		
		return repo.findOne(str);
		
	}

	@Override
	public List<ChatUser> findAll() {
	
		return repo.findAll();
	}

}
