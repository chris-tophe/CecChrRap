package vmc.javafxui.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BuildingCityBean {

	private SimpleIntegerProperty idBuildingCity;
	
	private SimpleStringProperty name;

	private SimpleStringProperty photoUrl;
	
	
	
	public BuildingCityBean() {
		this(0,"","");
	}
	
	public BuildingCityBean(int idBuildingCity, String name, String photoUrl) {
		this.idBuildingCity = new SimpleIntegerProperty(idBuildingCity);
		this.name = new SimpleStringProperty(name);
		this.photoUrl = new SimpleStringProperty(photoUrl);
	}
	
	
	public BuildingCityBean(BuildingBean buildingBean) {
		//this(buildingBean.getIdBuilding(),buildingBean.getName(),buildingBean.getPhotos().get(0));
		
		this.idBuildingCity = new SimpleIntegerProperty(buildingBean.getIdBuilding());
		this.name = new SimpleStringProperty(buildingBean.getName());
		this.photoUrl = new SimpleStringProperty(buildingBean.getPhotos().get(0));
		
		//this.idBuildingCity.set(buildingBean.getIdBuilding());
		//this.name.set(buildingBean.getName());
		//this.photoUrl.set(buildingBean.getPhotos().get(0));
	}
	
	

	public int getIdBuildingCity() {
		return idBuildingCity.get();
	}
	
	public void setIdBuildingCity(int idBuildingCity) {
		this.idBuildingCity.set(idBuildingCity);
	}

	public final SimpleIntegerProperty idBuildingCityProperty() {
		return idBuildingCity;
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
	
	
	public String getPhotoUrl() {
		return photoUrl.get();
	}
	
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl.set(photoUrl);
	}

	public final SimpleStringProperty photoUrlProperty() {
		return photoUrl;
	}
	
	@Override
	public String toString() {
		return "BuildingCityBean [idBuildingCity=" + idBuildingCity + ", name=" + name + ", photoUrl=" + photoUrl + "]";
	}
	
}
