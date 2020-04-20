package vmc.building.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Old_Address {

	@Id
	@GeneratedValue
	private int idAddress;
	
	private String streetName;
	

	public Old_Address() {
		
	}


	public Old_Address(int idAddress, String streetName) {
		super();
		this.idAddress = idAddress;
		this.streetName = streetName;
	}


	public int getIdAddress() {
		return idAddress;
	}


	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}


	public String getStreetName() {
		return streetName;
	}


	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}


	@Override
	public String toString() {
		return "Adress [idAddress=" + idAddress + ", streetName=" + streetName + "]";
	}

		
}