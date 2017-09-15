package be.formation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.formation.beans.Function;
import be.formation.repository.FunctionRepository;

@Service
public class FunctionServicesImpl implements FunctionServices {

	@Autowired
	private FunctionRepository repo;
	
	@Override
	public Function findOne(String str) {
		return repo.findOne(str);
	}

	@Override
	public List<Function> findAll() {
		return repo.findAll();
	}

	@Override
	public void switchEnable(String str) {
		Function fct =repo.findOne(str);
		fct.setOppositeIsActive();
		repo.save(fct);
	}

	@Override
	public List<Function> findAllActive(boolean isActive) {
		return repo.findByIsActive(true);
	}

	@Override
	public void createFunction(String str) {
		repo.save(new Function(str));
	}
}
