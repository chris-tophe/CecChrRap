package vmc.user.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	
	private String email;
	
	private String password;
	
	private int role;
	
	@ElementCollection
	private List<BuildingUser> buildings;
	
	public User() {
		this.buildings = new LinkedList<BuildingUser>();
	}

	public User(int idUser, String email, String password, int role, List<BuildingUser> buildings) {
		this.idUser = idUser;
		this.email = email;
		this.password = password;
		this.role = role;
		this.buildings = buildings;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int id) {
		this.idUser = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public List<BuildingUser> getBuildings() {
		return buildings;
	}

	public User addBuilding(BuildingUser building) {
		if (!this.buildings.contains(building)) {
			this.buildings.add(building);
		}
		return this;
	}
	
	public User removeBuilding(BuildingUser building) {
		if (this.buildings.contains(building)) {
			this.buildings.remove(building);
		}
		return this;
	}
	
}
