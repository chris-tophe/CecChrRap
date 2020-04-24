package vmc.javafxui.UiController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	private ImageView buildingPhotoView;
	
	AppMainUiController main;
	
	public void refresh() {
		BuildingBean building = main.getBuildingDetails();
		buildingName.setText(building.getName());
		buildingAddress.setText(building.getStreetNumber() + " " + building.getStreetName() + "\n"  + building.getCityAddress());
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
}
