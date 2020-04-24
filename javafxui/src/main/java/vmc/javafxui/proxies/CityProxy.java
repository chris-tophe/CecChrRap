package vmc.javafxui.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import vmc.javafxui.beans.BuildingCityBean;
import vmc.javafxui.beans.CityBean;

@FeignClient(name = "city", url = "localhost:9003")
public interface CityProxy {

	@GetMapping(value = "/city")
	public List<CityBean> getCities();
	
	@GetMapping(value = "/city/{id}")
	public CityBean getCityById(@PathVariable("id") int id);
	
	@GetMapping(value = "/city/name/{name}")
	public List<CityBean> getCitiesByName(@PathVariable("name") String name);
	
	@GetMapping(value = "/city/{id}/building")
	public List<BuildingCityBean> getBuildingByCityId(@PathVariable("id") int id);
	
	@GetMapping(value = "/city/{id}/building/{building}")
	public BuildingCityBean getBuildingByPosition
			(@PathVariable("id") int id , @PathVariable("building") int building);
	
	@PostMapping(value = "/city")
	public CityBean createCity(@RequestBody CityBean city);
	
	@PostMapping(value = "/city/{id}/building")
	public BuildingCityBean addBuildingToCity(@RequestBody BuildingCityBean building, @PathVariable("id") int id);
	
	@PutMapping(value = "/city")
	public CityBean updateCity(@RequestBody CityBean city);
	
	@PutMapping(value = "/city/{id}/building")
	public BuildingCityBean updateBuildingInCityList(@RequestBody BuildingCityBean building, @PathVariable("id") int id);
	
	@DeleteMapping(value = "/city/{id}")
	public ResponseEntity<CityBean> deleteCity(@PathVariable("id") int id);
	
	@DeleteMapping(value = "/city/{id}/building/{buildingId}")
	public ResponseEntity<BuildingCityBean> removeBuildingFromCity
			(@PathVariable("id") int id , @PathVariable("buildingId") int buildingId);

}