package vmc.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

import vmc.user.dao.BuildingUserDAO;
import vmc.user.dao.UserDAO;
import vmc.user.model.BuildingUser;
import vmc.user.model.User;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
	
//	@Bean
//	CommandLineRunner createUsers(UserDAO userRepo, BuildingUserDAO buildingRepo) {
//		return args -> {
//			Faker faker = new Faker();
//			for (int i = 0; i < 3; i++) {
//				User user = new User();
//				user.setEmail(faker.internet().emailAddress());
//				user.setPassword(faker.internet().password());
//				user.setRole(2);
//				for (int j = 0; j < 5; j++) {
//					BuildingUser building = new BuildingUser();
//					building.setName(faker.elderScrolls().city());
//					building.setPhoto(faker.avatar().image().toString());
//					user.addBuilding(building);
//					buildingRepo.save(building);
//				}
//				userRepo.save(user);
//			}
//		};
//	}
}
