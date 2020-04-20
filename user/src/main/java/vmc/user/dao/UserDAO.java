package vmc.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vmc.user.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

	public User findById(int id);
	
}
