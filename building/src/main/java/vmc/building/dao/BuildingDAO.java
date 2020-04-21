package vmc.building.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vmc.building.model.Building;

@Repository
public interface BuildingDAO extends JpaRepository<Building, Integer> {

	
}
