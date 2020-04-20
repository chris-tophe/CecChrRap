package vmc.user.model;


import java.awt.Point;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Building {

	@Id
	@GeneratedValue
	private int idBuilding;
	
	private String name;
	
	private String streetNumber;
	
	private String streetName;
	
	private String zipCode;
	
	private String city;
	
	private Point coordinate;

	@ElementCollection
	private List<String> photos;
	
	private String description;
	
	private Category category;
	
	private int constructionYear;
	
	private String architecte;
	
	@ElementCollection
	private List<Spec> specs;

	
	public Building() {
		
	}

  
	public Building(int idBuilding, String name, String streetNumber, String streetName, String zipCode, String city,
			Point coordinate, List<String> photos, String description, Category category, int constructionYear,
			String architecte, List<Spec> specs) {
		super();
		this.idBuilding = idBuilding;
		this.name = name;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.zipCode = zipCode;
		this.city = city;
		this.coordinate = coordinate;
		this.photos = photos;
		this.description = description;
		this.category = category;
		this.constructionYear = constructionYear;
		this.architecte = architecte;
		this.specs = specs;
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


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
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


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
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


	public List<Spec> getSpecs() {
		return specs;
	}


	public void setSpecs(List<Spec> specs) {
		this.specs = specs;
	}


	@Override
	public String toString() {
		return "Building [idBuilding=" + idBuilding + ", name=" + name + ", streetNumber=" + streetNumber
				+ ", streetName=" + streetName + ", zipCode=" + zipCode + ", city=" + city + ", coordinate="
				+ coordinate + ", photos=" + photos + ", description=" + description + ", category=" + category
				+ ", constructionYear=" + constructionYear + ", architecte=" + architecte + ", specs=" + specs + "]";
	}
	
}
