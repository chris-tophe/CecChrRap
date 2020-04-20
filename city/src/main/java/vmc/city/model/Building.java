package vmc.city.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Building {
	@Id
	@GeneratedValue
	int id;
	
	String name;
	
	public Building() {
		
	}
	
	public Building(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Building [id=" + id + ", name=" + name + "]";
	}
	
	
	
	

}
