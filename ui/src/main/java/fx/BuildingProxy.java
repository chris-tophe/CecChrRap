package fx;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "building", url = "localhost:9001")
public interface BuildingProxy {
	
	@GetMapping(value = "/Batiments")
	List<Building> listBuildings();

}
