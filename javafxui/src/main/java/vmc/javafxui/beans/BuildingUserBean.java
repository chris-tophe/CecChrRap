package vmc.javafxui.beans;

public class BuildingUserBean {
	
	private int idBuilding;
	private String name;
	private String photo;
	
	public BuildingUserBean() {
		
	}
	
	public BuildingUserBean( BuildingBean building ) {
		this.idBuilding = building.getIdBuilding();
		this.name = building.getName();
		this.photo = building.getPhotos().get(0);
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

	@Override
	public String toString() {
		return "BuildingUserBean [idBuilding=" + idBuilding + ", name=" + name + ", photo=" + photo + "]";
	}
	
}
