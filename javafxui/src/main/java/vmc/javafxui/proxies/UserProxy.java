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
import vmc.javafxui.beans.BuildingUserBean;
import vmc.javafxui.beans.UserBean;

@FeignClient (name = "user", url = "localhost:9002")
public interface UserProxy {

	@GetMapping(value = "/user")
	public List<UserBean> listUsers();
	
	@GetMapping(value = "/building")
	public List<BuildingUserBean> getAllBuildingUser();
	
	@GetMapping(value = "/user/{id}")
	public UserBean getOneUser(@PathVariable(name = "id") int id);
	
	@GetMapping(value = "/building/{id}")
	public BuildingUserBean getOneBuildingUserById(@PathVariable("id") int id);
	
	@PostMapping(value = "/user")
	public ResponseEntity<Void> addUser (@RequestBody UserBean user);
	
	@PostMapping(value = "/building")
	public BuildingUserBean addBuildingUser(@RequestBody BuildingUserBean buildingUser);
	
	@PutMapping(value = "/user")
	public UserBean updateUser(@RequestBody UserBean user);
	
	@PutMapping(value = "/building")
	public BuildingUserBean updateBuildingUser(@RequestBody BuildingUserBean buildingUser);
	
	@DeleteMapping(value = "/user/{id}")
	public void removeUser(@PathVariable(name = "id") int id);
	
	@DeleteMapping(value = "/building/{id}")
	public void removeBuildingUser(@PathVariable("id") int id);
}
