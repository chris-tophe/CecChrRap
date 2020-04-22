package vmc.javafxui.proxies;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import vmc.javafxui.beans.BuildingBean;


@FeignClient(name = "building", url = "localhost:9001")
public interface BuildingProxy {

	
	@GetMapping (value = "/building")
	public List<BuildingBean> listBuildings();
	
	@GetMapping (value = "/building/{id}")
	public Optional<BuildingBean> oneBuilding (@PathVariable int id);
	
	@PostMapping (value = "building")
	public ResponseEntity<Void> addBuilding (@RequestBody BuildingBean building);
	
	@PutMapping (value = "/building")
	public BuildingBean updateBuilding (@RequestBody BuildingBean building);
	
	@DeleteMapping (value = "/building/{id}")
	public void removeBuilding(@PathVariable int id);
	
	
}
