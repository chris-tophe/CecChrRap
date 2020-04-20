package vmc.city.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vmc.city.dao.CityDao;
import vmc.city.model.City;

@RestController
public class CityController {
	
	@Autowired
	CityDao cities;
	
	@GetMapping(value = "/city")
	public List<City> getCities() {
		return cities.findAll();
	}
	
	@GetMapping(value= "/city/{id}")
	public Optional<City> getCityById(@PathVariable int id){
		return cities.findById(id);
	}
	

}
