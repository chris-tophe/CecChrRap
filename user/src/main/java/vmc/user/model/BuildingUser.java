package vmc.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BuildingUser {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBuilding;

	private String name;
	
	private String photo;
	
	public BuildingUser() {
		
	}

	public BuildingUser(int idBuilding, String name, String photo) {
		super();
		this.idBuilding = idBuilding;
		this.name = name;
		this.photo = photo;
	}

	public int getIdBuilding() {
		return idBuilding;
	}

	public void setIdBuilding(int idBuilding) {
		this.idBuilding = idBuilding;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
