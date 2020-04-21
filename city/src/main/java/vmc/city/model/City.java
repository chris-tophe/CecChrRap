package vmc.city.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class City {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idCity;
	
	private String name;
	
	@ElementCollection
	private List<BuildingCity> buildings;
	
	public City(){
		this.buildings = new LinkedList<BuildingCity>();
	}
	
	public City(int idCity, String name , List<BuildingCity> buildings) {
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
	public List<BuildingCity> getBuildings() {
		return buildings;
	}
	public void setBuildings(List<BuildingCity> buildings) {
		this.buildings = buildings;
	}
	
	public void addBuilding (BuildingCity building) {
		this.buildings.add(building);
	}
	
	public void removeBuilding (BuildingCity building) {
		this.buildings.remove(building);
	}
	
	@Override
	public String toString() {
		return "City [idCity=" + idCity + ", name=" + name + ", buildings=" +buildings + "]";
	}

}
