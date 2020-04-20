package vmc.city.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class City {
	
	@Id
	@GeneratedValue
	private int idCity;
	
	private String name;
	
	@ElementCollection
	private List<Building> buildings;
	
	public City(){
		
	}
	
	public City(int idCity, String name , List<Building> buildings) {
		super();
		this.idCity = idCity;
		this.name = name;
		this.buildings = buildings;
	}


	public int getIdCity() {
		return idCity;
	}
	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Building> getBuildings() {
		return buildings;
	}
	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}
	
	public void addBuilding (Building building) {
		this.buildings.add(building);
	}
	
	public void removeBuilding (Building building) {
		this.buildings.remove(building);
	}
	
	@Override
	public String toString() {
		return "City [idCity=" + idCity + ", name=" + name + ", buildings=" +buildings + "]";
	}

}
