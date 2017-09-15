package be.formation.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="functions")
public class Function {

	@Id
	private String name;
	private String description="Description à pouvoir";
	private boolean isActive;
	
	public Function() {
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return name +", "+description+". ";
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
