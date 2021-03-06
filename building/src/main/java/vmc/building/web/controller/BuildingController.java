package vmc.building.web.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vmc.building.dao.BuildingDAO;
import vmc.building.model.Building;
import vmc.building.web.exceptions.BuildingNotFoundException;

@RestController
public class BuildingController {

	@Autowired
	private BuildingDAO BuildingDAO;
	
	
	@GetMapping (value = "/building")
	public List<Building> listBuildings(){
		
		return BuildingDAO.findAll();
		
	}
	
	@GetMapping (value = "/building/{id}")
	public Optional<Building> oneBuilding (@PathVariable int id) {
		
		Optional<Building> building = BuildingDAO.findById(id);
		
		if (building == null)
			throw new BuildingNotFoundException("La ville avec l'id " + id + " est introuvable.");
			return building;
	}
	
	@PostMapping (value = "building")
	public ResponseEntity<Building> addBuilding (@RequestBody Building building) {
		
		Building newBuilding = BuildingDAO.save(building);
    	
    	if(newBuilding == null)
    		return ResponseEntity.noContent().build();
    	
    	URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBuilding.getIdBuilding())
                .toUri();

        return ResponseEntity.created(location).body(newBuilding);
		
	}
	
	@PutMapping (value = "/building")
	public Building updateBuilding (@RequestBody Building building) {
		
		Optional<Building> selectedBuilding = BuildingDAO.findById(building.getIdBuilding());
		
		if(selectedBuilding.isPresent())
			return BuildingDAO.save(building);
		return null;
		
		
	}
	
	@DeleteMapping (value = "/building/{id}")
	public void removeBuilding(@PathVariable int id) {
		
		BuildingDAO.deleteById(id);
		
	}
	
	
	
	
}
