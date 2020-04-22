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

import vmc.javafxui.beans.UserBean;

@FeignClient (name = "user", url = "localhost:9002")
public interface UserProxy {

	@GetMapping(value = "/user")
	public List<UserBean> listUsers();
	
	@GetMapping(value = "/user/{id}")
	public UserBean getOneUser(@PathVariable(name = "id") int id);
	
	@PostMapping(value = "/user")
	public ResponseEntity<Void> addUser (@RequestBody UserBean user);
	
	@PutMapping(value = "/user")
	public UserBean updateUser(@RequestBody UserBean user);
	
	@DeleteMapping(value = "/user/{id}")
	public void removeUser(@PathVariable(name = "id") int id);
}
