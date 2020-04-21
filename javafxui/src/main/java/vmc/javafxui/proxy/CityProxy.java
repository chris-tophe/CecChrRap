package vmc.javafxui.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "city", url = "localhost:9003")
public interface CityProxy {

	@GetMapping(value = "/city")
	ResponseEntity<String> getCities();
}