package be.formation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import be.formation.beans.Function;

@Repository
public interface FunctionRepository extends JpaRepository<Function, String>{
	
public List<Function> findByIsActive(boolean isActive);
}
