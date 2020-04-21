package vmc.javafxui.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vmc.javafxui.beans.UserBean;

@FeignClient (name = "user", url = "localhost:9002")
public interface UserProxy {

	@GetMapping(value = "/user/{id}")
	public UserBean getOneUser(@PathVariable(name = "id") int id);
}
