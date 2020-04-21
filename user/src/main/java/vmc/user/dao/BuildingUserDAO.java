package vmc.user.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vmc.user.model.BuildingUser;

@Repository
public interface BuildingUserDAO extends JpaRepository<BuildingUser, Integer> {

	public Optional<BuildingUser> findById(int id);
}
