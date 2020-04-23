package vmc.javafxui.beans;

import java.util.LinkedList;
import java.util.List;

public class UserBean {

	private int idUser;
	private String email;
	private String password;
	private int role;
	private List<BuildingUserBean> buildings = new LinkedList<BuildingUserBean>();
	
	public UserBean() {
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

	public List<BuildingUserBean> getBuildings() {
		return buildings;
	}

	public UserBean addBuilding(BuildingUserBean building) {
		if (!this.buildings.contains(building)) {
			this.buildings.add(building);
		}
		return this;
	}
	
	public UserBean removeBuilding(BuildingUserBean building) {
		if (this.buildings.contains(building)) {
			this.buildings.remove(building);
		}
		return this;
	}
}

