package vmc.city;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

import vmc.city.dao.BuildingCityDao;
import vmc.city.dao.CityDao;
import vmc.city.model.BuildingCity;
import vmc.city.model.City;

@SpringBootApplication
public class CityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityApplication.class, args);
	}

//	@Bean
//	CommandLineRunner createCities(CityDao cityRepo, BuildingCityDao buildingRepo ) {
//		return args -> {
//			Faker faker = new Faker();
//			for(int i = 0; i<3; i++) {
//				City city = new City();
//				city.setName(faker.elderScrolls().region());
//				for (int j = 0; j<2;j++) {
//					BuildingCity building = new BuildingCity();
//					building.setName(faker.elderScrolls().city());
//					building.setPhotoUrl(faker.avatar().image().toString());
//					city.addBuilding(building);
//					buildingRepo.save(building);
//				}
//				cityRepo.save(city);
//			}	
//		};
//	}
}
