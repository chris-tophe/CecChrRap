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
	
	@GetMapping(value = "/building")
	public List<BuildingUser> listBuildingsUser() {
		return buildingUserDAO.findAll();
	}

	@GetMapping(value = "/user/{id}")
	public Optional<User> oneUser(@PathVariable int id) {
		Optional<User> user = userDAO.findById(id);
		if (user == null)
			throw new UserNotFoundException("L'utilisateur avec l'id " + id + " est introuvable.");
		return user;
	}
	
	@GetMapping(value = "/building/{id}")
	public Optional<BuildingUser> oneBuilding(@PathVariable int id) {
		Optional<BuildingUser> buildingUser = buildingUserDAO.findById(id);
		return buildingUser;
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
	
	@PostMapping(value = "/building")
	public ResponseEntity<BuildingUser> addBuildingUser(@RequestBody BuildingUser buildingUser) {
		BuildingUser newBuildingUser = buildingUserDAO.save(buildingUser);
		
    	if(newBuildingUser == null)
    		return ResponseEntity.noContent().build();
    	
    	URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBuildingUser.getIdBuilding())
                .toUri();

        return ResponseEntity.created(location).body(newBuildingUser);
	}

	@PutMapping(value = "/user")
	public User updateUser(@RequestBody User user) {
		Optional<User> selectedUser = userDAO.findById(user.getIdUser());
		if(selectedUser.isPresent()) {
			selectedUser.get().getBuildings().clear();
		/*for(BuildingUser b : selectedUser.get().getBuildings()) {
				selectedUser.get().getBuildings().remove(b);
				buildingUserDAO.deleteById(b.getId());
			}*/
		for(BuildingUser b : user.getBuildings()) {
			buildingUserDAO.save(b);
			}
		return userDAO.save(user);
		}	
		return null;
	}
	
	@PutMapping(value = "/building")
	public BuildingUser updateBuildingUser (@RequestBody BuildingUser buildingUser) {
		Optional<BuildingUser> selectedBuildingUser = buildingUserDAO.findById(buildingUser.getIdBuilding());
		
		if (selectedBuildingUser.isPresent()) {
			return buildingUserDAO.save(buildingUser);
		}
		return null;
	}

	@DeleteMapping(value = "/user/{id}")
	public void removeUser(@PathVariable int id) {
		userDAO.deleteById(id);
	}
	
	@DeleteMapping(value = "/building/{id}")
	public void removeBuildingUser(@PathVariable int id) {
		for (User user : userDAO.findAll()) {
			for (BuildingUser buildingUser : user.getBuildings()) {
				if (buildingUser.getIdBuilding() == id) {
					user.removeBuilding(buildingUserDAO.findById(id).get());
					userDAO.save(user);
				}
			}
		}
		buildingUserDAO.deleteById(id);
	}

}
