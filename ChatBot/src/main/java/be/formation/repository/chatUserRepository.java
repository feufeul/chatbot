package be.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.formation.beans.ChatUser;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, String>{
	
}
