package be.formation.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="functions")
public class Function {

	@Id
	private String name;
	private String shortDescription="Description à pourvoir";
	private String signature="!cmd_to_write arg0 arg1 ( for example )";
	private boolean isActive;
	
	public Function() {
	}
	
	
	public Function(String name, String shortDescription, String signature, boolean isActive) {
		super();
		this.name = name;
		this.shortDescription = shortDescription;
		this.signature = signature;
		this.isActive = isActive;
	}


	public String getShortDescription() {
		return shortDescription;
	}
	
	public void setShortDescription(String description) {
		this.shortDescription = description;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Function(String name) {
		super();
		this.name=name;
		this.isActive = false;
	}

	public Function(String name, boolean isActive) {
		super();
		this.name = name;
		this.isActive = isActive;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsActive() {
		return isActive;
	}
	
	public void setOppositeIsActive() {
		isActive = ! isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return name +", "+shortDescription+". ";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Function other = (Function) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
