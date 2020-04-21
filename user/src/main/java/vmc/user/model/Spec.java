package vmc.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Spec {

	@Id
	@GeneratedValue
	private int idSpec;
	
	private String name;
	
	public Spec() {
		
	}

	public Spec(int idSpec, String name) {
		super();
		this.idSpec = idSpec;
		this.name = name;
	}

	public int getIdSpec() {
		return idSpec;
	}

	public void setIdSpec(int idSpec) {
		this.idSpec = idSpec;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Spec [idSpec=" + idSpec + ", name=" + name + "]";
	}
	
	
	
}
