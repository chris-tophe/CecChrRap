package vmc.javafxui.beans;

import java.util.LinkedList;
import java.util.List;



public class CityBean {
	
	private int idCity;
	
	private String name;
	
	private List<BuildingCityBean> buildings;
	
	public CityBean(){
		this.buildings = new LinkedList<BuildingCityBean>();
	}
	
	public CityBean(int idCity, String name , List<BuildingCityBean> buildings) {
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
	public List<BuildingCityBean> getBuildings() {
		return buildings;
	}
	public void setBuildings(List<BuildingCityBean> buildings) {
		this.buildings = buildings;
	}
	
	public void addBuilding (BuildingCityBean building) {
		this.buildings.add(building);
	}
	
	public void removeBuilding (BuildingCityBean building) {
		this.buildings.remove(building);
	}
	
	@Override
	public String toString() {
		return "City [idCity=" + idCity + ", name=" + name + ", buildings=" +buildings + "]";
	}

}
