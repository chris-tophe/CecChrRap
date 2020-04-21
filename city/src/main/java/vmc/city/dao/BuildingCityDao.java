package vmc.city.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vmc.city.model.BuildingCity;

public interface BuildingCityDao extends JpaRepository<BuildingCity, Integer> {

}
