package vmc.user.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vmc.user.dao.BuildingUserDAO;
import vmc.user.dao.UserDAO;
import vmc.user.exceptions.UserNotFoundException;
import vmc.user.model.BuildingUser;
import vmc.user.model.User;

@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BuildingUserDAO buildingUserDAO;

	@GetMapping(value = "/user")
	public List<User> listUsers() {
		return userDAO.findAll();
	}

	@GetMapping(value = "/user/{id}")
	public Optional<User> oneUser(@PathVariable int id) {
		Optional<User> user = userDAO.findById(id);
		if (user == null)
			throw new UserNotFoundException("L'utilisateur avec l'id " + id + " est introuvable.");
		return user;
	}

	@PostMapping(value = "/user")
	public ResponseEntity<Void> addUser(@RequestBody User user) {
		User newUser = userDAO.save(user);
		if (newUser == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newUser.getIdUser())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/user")
	public User updateUser(@RequestBody User user) {
		Optional<User> selectedUser = userDAO.findById(user.getIdUser());
		for(BuildingUser b : user.getBuildings()) {
			Optional<BuildingUser> existingBuilding = buildingUserDAO.findById(b.getIdBuilding());
			if (!existingBuilding.isPresent())
				buildingUserDAO.save(b);
		}
		if(selectedUser.isPresent())
			return userDAO.save(user);
		return null;
	}

	@DeleteMapping(value = "/user/{id}")
	public void removeUser(@PathVariable int id) {
		userDAO.deleteById(id);
	}

}
