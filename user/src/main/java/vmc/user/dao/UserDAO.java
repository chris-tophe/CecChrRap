package vmc.user.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vmc.user.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

	public Optional<User> findById(int id);
	
}
