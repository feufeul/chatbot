package be.formation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import be.formation.beans.ChatUser;
import be.formation.beans.Message;
import be.formation.repository.MessageRepository;

@Service
public class MessageServicesImpl implements MessageServices {

	@Autowired
	private MessageRepository repo;
	
	@Override
	public void createMessage(String str, ChatUser usr) {
		
		repo.save(new Message(str, usr));
		
	}

	@Override
	public List<Message> showHistory() {

		return repo.findAll();
	
	}

	@Override
	public Page<Message> displayAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

}
