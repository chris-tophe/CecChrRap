package vmc.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vmc.user.dao.UserDAO;
import vmc.user.exceptions.UserNotFoundException;
import vmc.user.model.User;

@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@GetMapping (value = "/user")
	public List<User> listUsers(){
		return userDAO.findAll();
	}
	
	@GetMapping (value = "/user/{id}")
	public User oneUser(@PathVariable int id) {
		User user = userDAO.findById(id);
		
		if (user == null)
			throw new UserNotFoundException("L'utilisateur avec l'id " + id + " est introuvable.");
		return user;	
	}
	
}
