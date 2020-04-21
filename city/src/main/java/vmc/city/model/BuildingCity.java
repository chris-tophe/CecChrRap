package vmc.city.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class BuildingCity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBuildingCity;
	
	private String name;

	private String photoUrl;
	
	public BuildingCity() {
		
	}

	public BuildingCity(int idBuildingCity, String name, String photoUrl) {
		this.idBuildingCity = idBuildingCity;
		this.name = name;
		this.photoUrl = photoUrl;
	}

	public int getIdBuildingCity() {
		return idBuildingCity;
	}

	public void setIdBuildingCity(int idBuildingCity) {
		this.idBuildingCity = idBuildingCity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	@Override
	public String toString() {
		return "BuildingCity [idBuildingCity=" + idBuildingCity + ", name=" + name + ", photoUrl=" + photoUrl + "]";
	}





	
}
