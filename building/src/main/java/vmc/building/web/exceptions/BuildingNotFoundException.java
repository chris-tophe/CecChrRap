package vmc.building.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BuildingNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2803175368640074147L;

	public BuildingNotFoundException(String s) {
		super(s);
	}

	
	
}
