package vmc.javafxui.beans;


import java.awt.Point;
import java.util.List;



public class BuildingBean {

	private int idBuilding;
	
	private String name;
	
	private String streetNumber;
	
	private String streetName;
	
	private String zipCode;
	
	private String cityAddress;
	
	private Point coordinate;

	private List<String> photos;
	
	private String description;
	
	private CategoryBean category;
	
	private int constructionYear;
	
	private String architecte;
	

	
	public BuildingBean() {
		
	}

  
	public BuildingBean(int idBuilding, String name, String streetNumber, String streetName, String zipCode, String cityAddress,
			Point coordinate, List<String> photos, String description, CategoryBean category, int constructionYear,
			String architecte) {
		super();
		this.idBuilding = idBuilding;
		this.name = name;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.zipCode = zipCode;
		this.cityAddress = cityAddress;
		this.coordinate = coordinate;
		this.photos = photos;
		this.description = description;
		this.category = category;
		this.constructionYear = constructionYear;
		this.architecte = architecte;
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


	public String getStreetNumber() {
		return streetNumber;
	}


	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}


	public String getStreetName() {
		return streetName;
	}


	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getCityAddress() {
		return cityAddress;
	}


	public void setCityAddress(String cityAddress) {
		this.cityAddress = cityAddress;
	}


	public Point getCoordinate() {
		return coordinate;
	}


	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;

	}


	public List<String> getPhotos() {
		return photos;
	}


	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public CategoryBean getCategory() {
		return category;
	}


	public void setCategory(CategoryBean category) {
		this.category = category;
	}


	public int getConstructionYear() {
		return constructionYear;
	}


	public void setConstructionYear(int constructionYear) {
		this.constructionYear = constructionYear;
	}


	public String getArchitecte() {
		return architecte;
	}


	public void setArchitecte(String architecte) {
		this.architecte = architecte;
	}



	@Override
	public String toString() {
		return "Building [idBuilding=" + idBuilding + ", name=" + name + ", streetNumber=" + streetNumber
				+ ", streetName=" + streetName + ", zipCode=" + zipCode + ", cityAddress=" + cityAddress + ", coordinate="
				+ coordinate + ", photos=" + photos + ", description=" + description + ", category=" + category
				+ ", constructionYear=" + constructionYear + ", architecte=" + architecte + "]";
	}
	
}
