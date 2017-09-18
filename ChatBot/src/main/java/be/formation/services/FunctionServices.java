package be.formation.services;

import java.util.List;
import be.formation.beans.Function;

public interface FunctionServices {
	
	public Function findOne(String str);
	
	public List<Function> findAll();
	
	public void switchEnable(String str);
	
	public List<Function> findAllActive(boolean isActive);
	
	public void createFunction(String str);
	
	public void editFunction(String name, boolean isActive, String description, String signature);
}
