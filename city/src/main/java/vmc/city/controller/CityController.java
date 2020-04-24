package vmc.city.controller;

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
				return b.get(building);
			}
		}
		return null;
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

	@PutMapping(value = "/city")
	public City putCity(@RequestBody City city) {
		Optional<City> select = cities.findById(city.getIdCity());
		if (select.isPresent()) {
			return cities.save(city);
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

}
