package be.formation.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import be.formation.beans.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	
}
