package vmc.javafxui.UiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import vmc.javafxui.beans.BuildingBean;
import vmc.javafxui.proxies.BuildingProxy;

@Component
@Controller
public class BuildingUiController {

	private final HostServices hostServices;
	
	@Autowired
	private BuildingProxy buildingProxy;
	
	@FXML
	private Label buildingName, buildingAddress;
	
	@FXML
	private ImageView buildingPhotoView;
	
	public BuildingUiController(HostServices hostServices) {
		this.hostServices = hostServices;
	}
	
	@FXML
	public void initialize() {
		BuildingBean building = buildingProxy.oneBuilding(1);
		buildingName.setText(building.getName());
		buildingAddress.setText(building.getStreetNumber() + " " + building.getStreetName() + "\n"  + building.getCityAddress());
		String buildingPhotoUrl = building.getPhotos().get(1);
		Image buildingPhoto = new Image(buildingPhotoUrl);
		buildingPhotoView.setImage(buildingPhoto);
	}
}
