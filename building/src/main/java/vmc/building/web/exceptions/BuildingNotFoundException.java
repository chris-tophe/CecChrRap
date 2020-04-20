package vmc.building.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BuildingNotFoundException extends RuntimeException {

	public BuildingNotFoundException(String s) {
		super(s);
	}

	
	
}
