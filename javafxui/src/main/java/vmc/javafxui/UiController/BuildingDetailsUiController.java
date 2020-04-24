package vmc.javafxui.UiController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import vmc.javafxui.beans.BuildingBean;

@Component
@Controller
public class BuildingDetailsUiController implements Initializable {

	BuildingBean building;
	
	@FXML
	private Label buildingName, buildingAddress;
	
	@FXML
	Button addBuildingUserButton;
	
	@FXML
	private ImageView buildingPhotoView;
	
	AppMainUiController main;
	
	public void refresh() {
		this.building = main.getBuildingDetails();
		buildingName.setText(building.getName());
		
		String adress = building.getStreetNumber() + " " + building.getStreetName() + "\n"  + building.getZipCode() + " " + building.getCityAddress();
		String adressClean = adress.trim().replace(" +", " ");
				
		buildingAddress.setText(adressClean);
		
		String buildingPhotoUrl = building.getPhotos().get(0);
		Image buildingPhoto = new Image(buildingPhotoUrl);
		buildingPhotoView.setImage(buildingPhoto);
		
	}
	
	public void setMainApp(AppMainUiController mainApp) {
        
		this.main = mainApp;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void addBuildingToUserClick(Event event) {
		if (this.building != null) {
		this.main.addBuildingToUser(this.building);
		}
	}
}
