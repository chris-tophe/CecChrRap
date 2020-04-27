package vmc.city.controller;

import java.net.URI;
import java.util.LinkedList;
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

import vmc.city.dao.BuildingCityDao;
import vmc.city.dao.CityDao;
import vmc.city.model.BuildingCity;
import vmc.city.model.City;

@RestController
public class CityController {

	@Autowired
	CityDao cities;

	@Autowired
	BuildingCityDao buildings;

	@GetMapping(value = "/city")
	public List<City> getCities() {
		return cities.findAll();
	}
	
	@GetMapping(value = "/building")
	public List<BuildingCity> listBuildingsCity() {
		return buildings.findAll();
	}

	@GetMapping(value = "/city/{id}")
	public Optional<City> getCityById(@PathVariable int id) {
		return cities.findById(id);
	}

	@GetMapping(value = "/city/name/{name}")
	public List<City> getCityById(@PathVariable String name) {
		List<City> chosen = new LinkedList<City>();
		for (City c : cities.findAll()) {
			if (name.equals(c.getName())) {
				chosen.add(c);
			}
		}
		return chosen;
	}

	@GetMapping(value = "/city/{id}/building")
	public List<BuildingCity> getBuildings(@PathVariable int id) {
		Optional<City> c = cities.findById(id);
		if (c.isPresent()) {
			return c.get().getBuildings();
		}
		return null;
	}

	@GetMapping(value = "/city/{id}/building/{building}")
	public BuildingCity getBuildingByPosition(@PathVariable int id, @PathVariable int building) {
		Optional<City> c = cities.findById(id);
		if (c.isPresent()) {
			List<BuildingCity> b = c.get().getBuildings();
			if (building < b.size()) {
				return b.get(building - 1);
			}
		}
		return null;
	}
	
	@GetMapping(value = "/building/{id}")
	public Optional<BuildingCity> oneBuilding(@PathVariable int id) {
		Optional<BuildingCity> buildingCity = buildings.findById(id);
		return buildingCity;
	}

	@PostMapping(value = "/city")
	public City postCity(@RequestBody City city) {
		return cities.save(city);
	}

	@PostMapping(value = "/city/{id}/building")
	public BuildingCity postBuilding(@RequestBody BuildingCity building, @PathVariable int id) {
		Optional<City> c = cities.findById(id);
		buildings.save(building);
		c.get().addBuilding(building);
		cities.save(c.get());
		return building;
	}
	
	@PostMapping(value = "/building")
	public ResponseEntity<BuildingCity> addBuildingCity(@RequestBody BuildingCity buildingCity) {
		BuildingCity newBuildingCity = buildings.save(buildingCity);
		
    	if(newBuildingCity == null)
    		return ResponseEntity.noContent().build();
    	
    	URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBuildingCity.getIdBuildingCity())
                .toUri();

        return ResponseEntity.created(location).body(newBuildingCity);
	}

	@PutMapping(value = "/city")
	public City putCity(@RequestBody City city) {
		Optional<City> select = cities.findById(city.getIdCity());
		if (select.isPresent()) {
			return cities.save(city);
		}
		return null;
	}
	
	@PutMapping(value = "/city/{id}/building")
	public BuildingCity putBuilding(@RequestBody BuildingCity buildingCity, @PathVariable int id) {
		Optional<City> c = cities.findById(id);
		if (c.get().getBuildings() != null) {
			return buildings.save(buildingCity);
		}
		return null;
	}
	
	@PutMapping(value = "/building")
	public BuildingCity updateBuildingCity (@RequestBody BuildingCity buildingCity) {
		Optional<BuildingCity> selectedBuildingCity = buildings.findById(buildingCity.getIdBuildingCity());
		
		if (selectedBuildingCity.isPresent()) {
			return buildings.save(buildingCity);
		}
		return null;
	}

	@DeleteMapping(value = "/city/{id}")
	public ResponseEntity<City> deleteCity(@PathVariable int id) {
		if (cities.findAll().size() < id)
			return ResponseEntity.badRequest().build();
		cities.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/city/{id}/building/{buildingId}")
	public ResponseEntity<BuildingCity> deleteBuilding(@PathVariable int id, @PathVariable int buildingId) {
		cities.findById(id).get().getBuildings().removeIf(b -> (b.getIdBuildingCity() == buildingId));
		buildings.deleteById(buildingId);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value = "/building/{id}")
	public void removeBuildingCity(@PathVariable int id) {
		for (City city : cities.findAll()) {
			for (BuildingCity buildingCity : city.getBuildings()) {
				if (buildingCity.getIdBuildingCity() == id) {
					city.removeBuilding(buildings.findById(id).get());
					cities.save(city);
				}
			}
		}
		buildings.deleteById(id);
	}

}
