package vmc.javafxui.beans;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CityBean {
	
	private SimpleIntegerProperty idCity;
	
	private SimpleStringProperty name;
	
	private SimpleListProperty<BuildingCityBean> buildings;
	
	public CityBean(){
		this(0,"", new ArrayList<BuildingCityBean>());
		
	}
	
	public CityBean(int idCity, String name , List<BuildingCityBean> buildings) {
		this.idCity = new SimpleIntegerProperty( idCity);
		this.name = new SimpleStringProperty(name);
		this.buildings = new SimpleListProperty<BuildingCityBean>(FXCollections.observableList(buildings));
	}

	public int getIdCity() {
		return idCity.get();
	}
	public void setIdCity(int idCity) {
		this.idCity.set(idCity);
	}
	public final  SimpleIntegerProperty idCityProperty() {
		return idCity;
	}
	
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public final SimpleStringProperty nameProperty() {
		return name;
	}
	
	public List<BuildingCityBean> getBuildings() {
		return buildings.get();
	}
	public void setBuildings(List<BuildingCityBean> buildings) {
		this.buildings.set(FXCollections.observableList(buildings));
	}
	
	public void addBuilding (BuildingCityBean building) {
		this.buildings.add(building);
	}
	
	public void removeBuilding (BuildingCityBean building) {
		this.buildings.remove(building);
	}
	public final SimpleListProperty<BuildingCityBean> buildingsProperty(){
		return buildings;
	}
	
	@Override
	public String toString() {
		return "City [idCity=" + idCity + ", name=" + name + ", buildings=" +buildings + "]";
	}

}
