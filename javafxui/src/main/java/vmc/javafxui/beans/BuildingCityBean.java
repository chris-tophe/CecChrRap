package vmc.javafxui.beans;


public class BuildingCityBean {

	private int idBuildingCity;
	
	private String name;

	private String photoUrl;
	
	public BuildingCityBean() {
		
	}
	
	public BuildingCityBean(int idBuildingCity, String name, String photoUrl) {
		this.idBuildingCity = idBuildingCity;
		this.name = name;
		this.photoUrl = photoUrl;
	}
	
	public BuildingCityBean(BuildingBean buildingBean) {
		this.idBuildingCity = buildingBean.getIdBuilding();
		this.name = buildingBean.getName();
		this.photoUrl = buildingBean.getPhotos().get(0);
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
		return "BuildingCityBean [idBuildingCity=" + idBuildingCity + ", name=" + name + ", photoUrl=" + photoUrl + "]";
	}
	
}
