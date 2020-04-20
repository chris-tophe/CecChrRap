package vmc.city.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vmc.city.model.City;

public interface CityDao extends JpaRepository<City, Integer> {

}
