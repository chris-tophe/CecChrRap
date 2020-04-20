package vmc.building.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Building {

	@Id
	@GeneratedValue
	private int idBuilding;
	
	private String name;
	
	@OneToOne
	private Address address;
	
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


	public Building(int idBuilding, String name, Address address, List<String> photos, String description,
			Category category, int constructionYear, String architecte, List<Spec> specs) {
		super();
		this.idBuilding = idBuilding;
		this.name = name;
		this.address = address;
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


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
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
		return "Building [idBuilding=" + idBuilding + ", name=" + name + ", address=" + address + ", photos=" + photos
				+ ", description=" + description + ", category=" + category + ", constructionYear=" + constructionYear
				+ ", architecte=" + architecte + ", specs=" + specs + "]";
	}
	
	
}
